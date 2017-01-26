package com.shellcore.android.messaging.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shellcore.android.messaging.domain.FirebaseHelper;
import com.shellcore.android.messaging.entities.User;
import com.shellcore.android.messaging.events.LoginEvent;
import com.shellcore.android.messaging.libs.EventBus;
import com.shellcore.android.messaging.libs.GreenRobotEventBus;

/**
 * Created by Cesar on 25/01/2017.
 */

public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseHelper helper;
    private FirebaseAuth authReference;
    private FirebaseDatabase databaseReference;
    private DatabaseReference myUserReference;

    public LoginRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.authReference = helper.getAuthReference();
        this.databaseReference = helper.getDatabaseReference();
        this.myUserReference = helper.getMyUserReference();
    }

    @Override
    public void signIn(String email, String password) {
        authReference.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            myUserReference = helper.getMyUserReference();
                            myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    User currentUser = dataSnapshot.getValue(User.class);

                                    if (currentUser == null) {
                                        String email = helper.getAuthUserEmail();
                                        if (email != null) {
                                            currentUser = new User();
                                            myUserReference.setValue(currentUser);
                                        }
                                    }
                                    helper.changeUserConnectionStatus(User.ONLINE);
                                    postEvent(LoginEvent.onSigninSuccess);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {}
                            });
                        } else {
                            postEvent(LoginEvent.onSigninError, task.getException().getMessage());
                        }
                    }
                });
    }

    @Override
    public void signUp(final String email, final String password) {
        authReference.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            postEvent(LoginEvent.onSignupSuccess);
                            signIn(email, password);
                        } else {
                            postEvent(LoginEvent.onSignupError, task.getException().getMessage());
                        }
                    }
                });


//        postEvent(LoginEvent.onSignupSuccess);
    }

    @Override
    public void checkSession() {
        postEvent(LoginEvent.onFailedToRecoverySession);
    }

    private void postEvent(int eventType) {
        postEvent(eventType, null);
    }

    private void postEvent(int eventType, String errorMessage) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(eventType);
        if (errorMessage != null) {
            loginEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }
}
