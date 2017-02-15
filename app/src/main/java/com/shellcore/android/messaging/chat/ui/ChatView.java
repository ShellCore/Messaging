package com.shellcore.android.messaging.chat.ui;

import com.shellcore.android.messaging.entities.ChatMessage;

/**
 * Created by Cesar on 14/02/2017.
 */

public interface ChatView {
    void onMessageReceived(ChatMessage msg);
}
