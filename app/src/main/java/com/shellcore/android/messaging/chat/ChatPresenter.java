package com.shellcore.android.messaging.chat;

import com.shellcore.android.messaging.chat.events.ChatEvent;

/**
 * Created by Cesar on 14/02/2017.
 */

public interface ChatPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String recipient);
    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);
}
