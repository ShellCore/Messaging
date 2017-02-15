package com.shellcore.android.messaging.contactList.ui.adapters;

import com.shellcore.android.messaging.entities.User;

/**
 * Created by Cesar on 31/01/2017.
 */

public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
