package com.shellcore.android.messaging.chat;

import com.shellcore.android.messaging.chat.events.ChatEvent;
import com.shellcore.android.messaging.chat.ui.ChatView;
import com.shellcore.android.messaging.entities.User;
import com.shellcore.android.messaging.libs.EventBus;
import com.shellcore.android.messaging.libs.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 14/02/2017.
 */
public class ChatPresenterImpl implements ChatPresenter {

    // Variables
    private ChatView view;

    // Servicios
    private EventBus eventBus;
    private ChatInteractor interactor;
    private ChatSessionInteractor sessionInteractor;

    public ChatPresenterImpl(ChatView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new ChatInteractorImpl();
        this.sessionInteractor = new ChatSessionInteractorImpl();
    }

    @Override
    public void onPause() {
        interactor.unsubscribe();
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onResume() {
        interactor.subscribe();
        sessionInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        interactor.destroyListener();
        view = null;
    }

    @Override
    public void setChatRecipient(String recipient) {
        interactor.setRecipient(recipient);
    }

    @Override
    public void sendMessage(String msg) {
        interactor.sendMessage(msg);
    }

    @Subscribe
    @Override
    public void onEventMainThread(ChatEvent event) {
        if (view != null) {
            view.onMessageReceived(event.getChatMessage());
        }
    }
}
