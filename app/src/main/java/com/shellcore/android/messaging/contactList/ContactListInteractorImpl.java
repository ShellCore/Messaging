package com.shellcore.android.messaging.contactList;

/**
 * Created by Cesar on 13/02/2017.
 */
public class ContactListInteractorImpl implements ContactListInteractor {

    // Servicios
    private ContactListRepository repository;

    public ContactListInteractorImpl() {
        this.repository = new ContactListRepositoryImpl();
    }

    @Override
    public void subscribe() {
        repository.subscribeToContactListEvents();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribeToContactListEvents();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }

    @Override
    public void removeContact(String email) {
        repository.removeContact(email);
    }
}
