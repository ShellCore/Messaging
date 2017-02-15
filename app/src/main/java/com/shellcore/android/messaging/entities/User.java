package com.shellcore.android.messaging.entities;

import java.util.Map;

/**
 * Created by Cesar on 26/01/2017.
 */
public class User {
    public static final boolean ONLINE = true;
    public static final boolean OFFLINE = false;

    String email;
    boolean online;
    Map<String, Boolean> contacts;

    public User() {
    }

    public User(String email, boolean online) {
        this.email = email;
        this.online = online;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Map<String, Boolean> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Boolean> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if (obj instanceof User) {
            User recipe = (User) obj;
            equal = this.email.equals(recipe.getEmail());
        }

        return equal;
    }
}
