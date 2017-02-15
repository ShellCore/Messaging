package com.shellcore.android.messaging.login;

/**
 * Created by Cesar on 25/01/2017.
 */

public interface LoginInteractor {
    void checkSession();
    void doSignup(String email, String password);
    void doSignin(String email, String password);
}
