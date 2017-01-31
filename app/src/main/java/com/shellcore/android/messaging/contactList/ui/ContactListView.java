package com.shellcore.android.messaging.contactList.ui;

import com.shellcore.android.messaging.entities.User;

/**
 * Created by Cesar on 31/01/2017.
 */

public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
