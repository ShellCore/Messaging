package com.shellcore.android.messaging.createAccount;

/**
 * Created by Cesar on 15/02/2017.
 */
public class CreateAccountInteractorImpl implements CreateAccountInteractor {

    // Servicios
    private CreateAccountRepository repository;

    public CreateAccountInteractorImpl() {
        repository = new CreateAccountRepositoryImpl();
    }

    @Override
    public void doSignup(String email, String password) {
        repository.signUp(email, password);
    }
}
