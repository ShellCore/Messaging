package com.shellcore.android.messaging.addContact.ui;

/**
 * Created by Cesar on 14/02/2017.
 */

public interface AddContactView {
    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void contactAdded();
    void contactNotAdded();
}
