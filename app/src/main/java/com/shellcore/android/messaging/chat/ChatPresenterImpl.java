package com.shellcore.android.messaging.chat;

import com.shellcore.android.messaging.chat.events.ChatEvent;
import com.shellcore.android.messaging.chat.ui.ChatView;
import com.shellcore.android.messaging.libs.EventBus;

/**
 * Created by Cesar on 14/02/2017.
 */
public class ChatPresenterImpl implements ChatPresenter {

    // Servicios
    private ChatView view;

    // Variables
    private ChatInteractor interactor;
    private ChatSessionInteractor sessionInteractor;

    public ChatPresenterImpl(ChatView view) {
        this.view = view;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void setChatRecipient(String recipient) {

    }

    @Override
    public void sendMessage(String msg) {

    }

    @Override
    public void onEventMainThread(ChatEvent event) {

    }
}
