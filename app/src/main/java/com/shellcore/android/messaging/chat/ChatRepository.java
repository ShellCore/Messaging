package com.shellcore.android.messaging.chat;

/**
 * Created by Cesar on 14/02/2017.
 */

public interface ChatRepository {
    void changeConnectionStatus(boolean online);

    void sendMessage(String message);
    void setRecipient(String recipient);

    void subscribe();
    void unsubscribe();
    void destroyListener();
}
