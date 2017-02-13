package com.shellcore.android.messaging.contactList;

/**
 * Created by Cesar on 13/02/2017.
 */
public class ContactListSessionInteractorImpl implements ContactListSessionInteractor {

    // Servicios
    private ContactListRepository repository;

    public ContactListSessionInteractorImpl() {
        this.repository = new ContactListRepositoryImpl();
    }

    @Override
    public void signoff() {
        repository.signoff();
    }

    @Override
    public String getCurrentUserEmail() {
        return repository.getCurrentUserEmail();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        repository.changeConnectionStatus(online);
    }
}
