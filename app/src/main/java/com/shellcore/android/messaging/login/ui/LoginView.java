package com.shellcore.android.messaging.login.ui;

/**
 * Created by Cesar on 25/01/2017.
 */

public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignin();
    void handleSignup();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);
}
