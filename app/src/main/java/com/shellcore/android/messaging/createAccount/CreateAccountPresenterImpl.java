package com.shellcore.android.messaging.createAccount;

import com.shellcore.android.messaging.createAccount.events.CreateAccountEvent;
import com.shellcore.android.messaging.createAccount.ui.CreateAccountActivity;
import com.shellcore.android.messaging.createAccount.ui.CreateAccountView;
import com.shellcore.android.messaging.libs.EventBus;
import com.shellcore.android.messaging.libs.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 15/02/2017.
 */
public class CreateAccountPresenterImpl implements com.shellcore.android.messaging.createAccount.CreateAccountPresenter {

    // Variables
    private CreateAccountView view;

    // Servicios
    private EventBus eventBus;
    private CreateAccountInteractor interactor;


    public CreateAccountPresenterImpl(CreateAccountView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        interactor = new CreateAccountInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void registerNewUser(String email, String password) {
        if (view != null) {
            view.disableInput();
            view.showProgress();
        }

        interactor.doSignup(email, password);
    }

    @Subscribe
    @Override
    public void onEventMainThread(CreateAccountEvent event) {
        switch (event.getEventType()) {
            case CreateAccountEvent.onSigninError:
                onSigninError(event.getErrorMessage());
                break;
            case CreateAccountEvent.onSignupError:
                onSignupError(event.getErrorMessage());
                break;
            case CreateAccountEvent.onSigninSuccess:
                onSigninSuccess();
                break;
            case CreateAccountEvent.onSignupSuccess:
                onSignupSuccess();
                break;
        }
    }

    private void onSigninError(String errorMessage) {
        if (view != null) {
            view.hideProgress();
            view.enableInput();
            view.loginError(errorMessage);
        }
    }

    private void onSignupError(String errorMessage) {
        if (view != null) {
            view.hideProgress();
            view.enableInput();
            view.newUserError(errorMessage);
        }
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
}
