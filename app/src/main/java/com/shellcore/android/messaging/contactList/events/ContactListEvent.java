package com.shellcore.android.messaging.contactList.events;

import com.shellcore.android.messaging.entities.User;

/**
 * Created by Cesar on 31/01/2017.
 */
public class ContactListEvent {
    public static final int onContactAdded = 1;
    public static final int onContactChanged = 2;
    public static final int onContactRemoved = 3;

    private User user;
    private int eventType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
