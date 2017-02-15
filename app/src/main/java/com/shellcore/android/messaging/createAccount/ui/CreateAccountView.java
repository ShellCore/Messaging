package com.shellcore.android.messaging.createAccount.ui;

/**
 * Created by Cesar on 15/02/2017.
 */

public interface CreateAccountView {
    void enableInput();
    void disableInput();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleCancel();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);
}
