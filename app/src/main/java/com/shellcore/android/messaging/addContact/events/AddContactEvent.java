package com.shellcore.android.messaging.addContact.events;

/**
 * Created by Cesar on 14/02/2017.
 */
public class AddContactEvent {
    boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
