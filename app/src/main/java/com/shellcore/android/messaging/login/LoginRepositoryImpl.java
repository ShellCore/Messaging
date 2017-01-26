package com.shellcore.android.messaging.login;

import com.shellcore.android.messaging.domain.FirebaseHelper;
import com.shellcore.android.messaging.events.LoginEvent;
import com.shellcore.android.messaging.libs.EventBus;
import com.shellcore.android.messaging.libs.GreenRobotEventBus;

/**
 * Created by Cesar on 25/01/2017.
 */

public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseHelper helper;

    public LoginRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void signIn(String email, String password) {
        postEvent(LoginEvent.onSigninSuccess);
    }

    @Override
    public void signUp(String email, String password) {
        postEvent(LoginEvent.onSignupSuccess);
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
