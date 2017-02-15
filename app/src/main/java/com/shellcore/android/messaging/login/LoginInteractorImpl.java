package com.shellcore.android.messaging.login;

/**
 * Created by Cesar on 25/01/2017.
 */

public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository repository;

    public LoginInteractorImpl() {
        this.repository = new LoginRepositoryImpl();
    }

    @Override
    public void checkSession() {
        repository.checkSession();
    }

    @Override
    public void doSignup(String email, String password) {
        repository.signUp(email, password);
    }

    @Override
    public void doSignin(String email, String password) {
        repository.signIn(email, password);
    }
}
