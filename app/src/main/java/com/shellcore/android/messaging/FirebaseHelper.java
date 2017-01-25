package com.shellcore.android.messaging;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Cesar on 25/01/2017.
 */

public class FirebaseHelper {
    public static final String USERS_PATH = "users";
    public static final String CONTACTS_PATH = "contacts";
    public static final String CHATS_PATH = "chats";
    public static final String SEPARATOR = "___";

    private FirebaseAuth authReference;

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
}
