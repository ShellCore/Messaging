package com.shellcore.android.messaging.chat.events;

import com.shellcore.android.messaging.entities.ChatMessage;

/**
 * Created by Cesar on 14/02/2017.
 */

public class ChatEvent {
    private ChatMessage chatMessage;

    public ChatMessage getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
    }
}
