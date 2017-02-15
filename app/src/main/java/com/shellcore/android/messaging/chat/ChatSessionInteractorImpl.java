package com.shellcore.android.messaging.chat;

/**
 * Created by Cesar on 15/02/2017.
 */
public class ChatSessionInteractorImpl implements ChatSessionInteractor {

    // Servicios
    private ChatRepository repository;

    public ChatSessionInteractorImpl() {
        this.repository = new ChatRepositoryImpl();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        repository.changeConnectionStatus(online);
    }
}
