package com.shellcore.android.messaging.chat;

/**
 * Created by Cesar on 14/02/2017.
 */

public interface ChatInteractor {
    void sendMessage(String msg);
    void setRecipient(String recipient);

    void subscribe();
    void unsubscribe();
    void destroyListener();
}
