package com.shellcore.android.messaging.addContact;

import com.shellcore.android.messaging.addContact.events.AddContactEvent;
import com.shellcore.android.messaging.addContact.ui.AddContactView;

/**
 * Created by Cesar on 14/02/2017.
 */
public class AddContactPresenterImpl implements AddContactPresenter {

    // Variables
    private AddContactView view;

    public AddContactPresenterImpl(AddContactView view) {
        this.view = view;
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void addContact(String email) {

    }

    @Override
    public void onEventMainThread(AddContactEvent event) {

    }
}
