package com.shellcore.android.messaging.contactList;

import com.shellcore.android.messaging.contactList.events.ContactListEvent;

/**
 * Created by Cesar on 31/01/2017.
 */

public interface ContactListPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);

    void onEventMainThread(ContactListEvent event);
}
