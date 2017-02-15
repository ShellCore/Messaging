package com.shellcore.android.messaging.contactList;

/**
 * Created by Cesar on 31/01/2017.
 */

public interface ContactListRepository {
    void signoff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void destroyListener();
    void subscribeToContactListEvents();
    void unsubscribeToContactListEvents();
    void changeConnectionStatus(boolean online);
}
