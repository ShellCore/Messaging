package com.shellcore.android.messaging.createAccount.ui;

/**
 * Created by Cesar on 15/02/2017.
 */

public interface CreateAccountView {
    void enableInputf();
    void disableInput();
    void showProgress();
    void hideProgress();

    void handleSignUp();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);
}
