package com.shellcore.android.messaging.addContact;

import com.shellcore.android.messaging.addContact.events.AddContactEvent;

/**
 * Created by Cesar on 14/02/2017.
 */

public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
