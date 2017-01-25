package com.shellcore.android.messaging.domain;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cesar on 25/01/2017.
 */

public class FirebaseHelper {
    public static final String USERS_PATH = "users";
    public static final String CONTACTS_PATH = "contacts";
    public static final String CHATS_PATH = "chats";
    public static final String SEPARATOR = "___";

    private FirebaseAuth authReference;
    private FirebaseDatabase databaseReference;

    private static class SingletonHolder {
        public static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public FirebaseHelper() {
        authReference = FirebaseAuth.getInstance();
    }

    public String getAuthUserEmail() {
        String email = null;
        FirebaseUser user = authReference.getCurrentUser();
        if (user != null) {
            email = user.getEmail();
        }
        return email;
    }

    public DatabaseReference getUserReference(String email) {
        DatabaseReference userReference = null;
        if (email != null && !email.isEmpty()) {
            String emailKey = email.replace(".", "_");
            userReference = databaseReference.getReference()
                    .child(USERS_PATH)
                    .child(emailKey);
        }
        return userReference;
    }

    public DatabaseReference getMyUserReference() {
        return getUserReference(getAuthUserEmail());
    }

    public DatabaseReference getContactsReference(String email) {
        return getUserReference(email)
                .child(CONTACTS_PATH);
    }

    public DatabaseReference getMyContactsReference() {
        return getContactsReference(getAuthUserEmail());
    }

    public DatabaseReference getOneContactReference(String email, String childEmail) {
        String childKey = childEmail.replace(".", "_");
        return getContactsReference(email)
                .child(childKey);
    }

    public DatabaseReference getChatsReference(String receiver) {
        String keySender = getAuthUserEmail().replace(".", "_");
        String keyReceiver = receiver.replace(".", "_");

        String keyChat = keySender + SEPARATOR + keyReceiver;
        if (keySender.compareTo(keyReceiver) > 0) {
            keyChat = keyReceiver + SEPARATOR + keySender;
        }
        return databaseReference.getReference()
                .child(CHATS_PATH)
                .child(keyChat);
    }

    public void changeUserConnectionStatus(boolean online) {
        if (getMyUserReference() != null) {
            Map<String, Object> updates = new HashMap<>();
            updates.put("online", online);
            getMyUserReference().updateChildren(updates);
            notifyContactsOfConnectionChanged(online);
        }
    }

    // Función solo avisa del cambio de estado de conexión
    public void notifyContactsOfConnectionChanged(boolean online) {
        notifyContactsOfConnectionChanged(online, false);
    }

    public void signOff() {
        notifyContactsOfConnectionChanged(false, true);
    }

    private void notifyContactsOfConnectionChanged(final boolean online, final boolean signoff) {
        final String myEmail = getAuthUserEmail();
        getMyContactsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String email = child.getKey();
                    DatabaseReference reference = getOneContactReference(email, myEmail);
                    reference.setValue(online);
                }
                if (signoff) {
                    authReference.signOut();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
