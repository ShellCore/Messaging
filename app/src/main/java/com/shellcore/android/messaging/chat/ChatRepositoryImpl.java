package com.shellcore.android.messaging.chat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.shellcore.android.messaging.chat.events.ChatEvent;
import com.shellcore.android.messaging.domain.FirebaseHelper;
import com.shellcore.android.messaging.entities.ChatMessage;
import com.shellcore.android.messaging.libs.EventBus;
import com.shellcore.android.messaging.libs.GreenRobotEventBus;

/**
 * Created by Cesar on 15/02/2017.
 */
public class ChatRepositoryImpl implements ChatRepository {

    // Variables
    private String recipient;
    private ChildEventListener chatEventListener;

    // Servicios
    private FirebaseHelper helper;
    private EventBus eventBus;

    public ChatRepositoryImpl() {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }

    @Override
    public void sendMessage(String message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(helper.getAuthUserEmail());
        chatMessage.setMsg(message);

        DatabaseReference chatsReference = helper.getChatsReference(recipient);
        chatsReference.push()
                .setValue(chatMessage);
    }

    @Override
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public void subscribe() {
        if (chatEventListener == null) {
            chatEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                    String msgSender = chatMessage.getSender();

                    chatMessage.setSentByMe(msgSender.equals(helper.getAuthUserEmail()));

                    ChatEvent event = new ChatEvent();
                    event.setChatMessage(chatMessage);
                    eventBus.post(event);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
        }
        helper.getChatsReference(recipient)
                .addChildEventListener(chatEventListener);
    }

    @Override
    public void unsubscribe() {
        if (chatEventListener != null) {
            helper.getChatsReference(recipient)
                    .removeEventListener(chatEventListener);
        }
    }

    @Override
    public void destroyListener() {
        chatEventListener = null;
    }
}
