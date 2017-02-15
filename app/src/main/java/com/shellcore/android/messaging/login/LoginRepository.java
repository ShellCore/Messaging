package com.shellcore.android.messaging.login;

/**
 * Created by Cesar on 25/01/2017.
 */

public interface LoginRepository {
    void signIn(String email, String password);
    void signUp(String email, String password);
    void checkSession();
}
