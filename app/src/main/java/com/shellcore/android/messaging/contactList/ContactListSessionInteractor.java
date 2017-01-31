package com.shellcore.android.messaging.contactList;

/**
 * Created by Cesar on 31/01/2017.
 */

public interface ContactListSessionInteractor {
    void signoff();
    String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);
}
