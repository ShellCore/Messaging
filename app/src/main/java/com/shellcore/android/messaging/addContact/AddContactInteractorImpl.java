package com.shellcore.android.messaging.addContact;

/**
 * Created by Cesar on 14/02/2017.
 */
public class AddContactInteractorImpl implements AddContactInteractor {

    // Servicios
    private AddContactRepository repository;

    public AddContactInteractorImpl() {
        repository = new AddContactRepositoryImpl();
    }

    @Override
    public void execute(String email) {
        repository.addContact(email);
    }
}
