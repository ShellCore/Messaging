package com.shellcore.android.messaging.login;

import android.util.Log;

import com.shellcore.android.messaging.events.LoginEvent;
import com.shellcore.android.messaging.libs.EventBus;
import com.shellcore.android.messaging.libs.GreenRobotEventBus;
import com.shellcore.android.messaging.login.ui.LoginView;

/**
 * Created by Cesar on 25/01/2017.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private EventBus eventBus;
    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        this.interactor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser() {
        if (view != null) {
            view.disableInputs();
            view.showProgress();
        }
        interactor.checkSession();
    }

    @Override
    public void validateLogin(String email, String password) {
        if (view != null) {
            view.disableInputs();
            view.showProgress();
        }
        interactor.doSignin(email, password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if (view != null) {
            view.disableInputs();
            view.showProgress();
        }
        interactor.doSignup(email, password);
    }


    @Override
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()) {
            case LoginEvent.onSigninError:
                onSigninError(event.getErrorMessage());
                break;
            case LoginEvent.onSignupError:
                onSignupError(event.getErrorMessage());
                break;
            case LoginEvent.onSigninSuccess:
                onSigninSuccess();
                break;
            case LoginEvent.onSignupSuccess:
                onSignupSuccess();
                break;
            case LoginEvent.onFailedToRecoverySession:
                onEventFailedToRecoverySession();
                break;
        }
    }

    private void onEventFailedToRecoverySession() {
        if (view != null) {
            view.hideProgress();
            view.enableInputs();
        }
        Log.e("LoginPresenterImpl", "onEventFailedToRecoverySession");
    }

    private void onSigninSuccess() {
        if (view != null) {
            view.navigateToMainScreen();
        }
    }

    private void onSignupSuccess() {
        if (view != null) {
            view.newUserSuccess();
        }
    }

    private void onSigninError(String error) {
        if (view != null) {
            view.hideProgress();
            view.enableInputs();
            view.loginError(error);
        }
    }

    private void onSignupError(String error) {
        if (view != null) {
            view.hideProgress();
            view.enableInputs();
            view.newUserError(error);
        }
    }
}
