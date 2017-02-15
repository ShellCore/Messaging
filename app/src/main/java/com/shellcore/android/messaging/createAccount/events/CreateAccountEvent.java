package com.shellcore.android.messaging.createAccount.events;

/**
 * Created by Cesar on 15/02/2017.
 */

public class CreateAccountEvent {
    public static final int onSigninError = 1;
    public static final int onSignupError = 2;
    public static final int onSigninSuccess = 3;
    public static final int onSignupSuccess = 4;
    public static final int onFailedToRecoverySession = 5;

    private int eventType;
    private String errorMessage;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
