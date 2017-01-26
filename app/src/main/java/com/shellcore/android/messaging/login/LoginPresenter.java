package com.shellcore.android.messaging.login;

import com.shellcore.android.messaging.events.LoginEvent;

/**
 * Created by Cesar on 25/01/2017.
 */

public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);
}
