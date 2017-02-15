package com.shellcore.android.messaging.contactList;

/**
 * Created by Cesar on 31/01/2017.
 */

public interface ContactListInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeContact(String email);
}
