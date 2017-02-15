package com.shellcore.android.messaging.entities;

import com.google.firebase.database.Exclude;

/**
 * Created by Cesar on 14/02/2017.
 */

public class ChatMessage {
    private String msg;
    private String sender;
    private boolean sentByMe;

    public ChatMessage() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Exclude
    public boolean isSentByMe() {
        return sentByMe;
    }

    public void setSentByMe(boolean sentByMe) {
        this.sentByMe = sentByMe;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if (obj instanceof ChatMessage) {
            ChatMessage recipe = (ChatMessage) obj;
            equal = this.sender.equals(recipe.getSender())
                    && this.msg.equals(recipe.getMsg())
                    && this.sentByMe == recipe.isSentByMe();
        }

        return equal;
    }
}
