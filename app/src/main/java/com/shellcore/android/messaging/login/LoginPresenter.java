package com.shellcore.android.messaging.login;

/**
 * Created by Cesar on 25/01/2017.
 */

public interface LoginPresenter {
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
}
