package com.shellcore.android.messaging.contactList;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.shellcore.android.messaging.contactList.events.ContactListEvent;
import com.shellcore.android.messaging.domain.FirebaseHelper;
import com.shellcore.android.messaging.entities.User;
import com.shellcore.android.messaging.libs.EventBus;
import com.shellcore.android.messaging.libs.GreenRobotEventBus;

/**
 * Created by Cesar on 13/02/2017.
 */
public class ContactListRepositoryImpl implements ContactListRepository {

    // Servicios
    private FirebaseHelper helper;
    private EventBus eventBus;
    private ChildEventListener contactEventListener;

    public ContactListRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void signoff() {
        helper.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return helper.getAuthUserEmail();
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail = helper.getAuthUserEmail();
        helper.getOneContactReference(currentUserEmail, email)
                .removeValue();
        helper.getOneContactReference(email, currentUserEmail)
                .removeValue();
    }

    @Override
    public void destroyListener() {
        contactEventListener = null;
    }

    @Override
    public void subscribeToContactListEvents() {
        if (contactEventListener == null) {
            contactEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, ContactListEvent.onContactAdded);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, ContactListEvent.onContactChanged);

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleContact(dataSnapshot, ContactListEvent.onContactRemoved);

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };
        }
        helper.getMyContactsReference()
                .addChildEventListener(contactEventListener);
    }

    @Override
    public void unsubscribeToContactListEvents() {
        if (contactEventListener != null) {
            helper.getMyContactsReference()
                    .removeEventListener(contactEventListener);
        }
    }

    @Override
    public void changeConnectionStatus(boolean online) {

    }

    private void post(int type, User user) {
        ContactListEvent event = new ContactListEvent();
        event.setEventType(type);
        event.setUser(user);
        eventBus.post(event);
    }

    private void handleContact(DataSnapshot dataSnapshot, int type) {
        String email = dataSnapshot.getKey();
        email = email.replace("_", ".");
        boolean online = ((Boolean) dataSnapshot.getValue()).booleanValue();
        User user = new User(email, online);
        post(type, user);
    }
}
