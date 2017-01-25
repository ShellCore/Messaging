package com.shellcore.android.messaging.login;

import com.shellcore.android.messaging.domain.FirebaseHelper;

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

    }

    @Override
    public void signUp(String email, String password) {

    }

    @Override
    public void checkSession() {

    }
}
