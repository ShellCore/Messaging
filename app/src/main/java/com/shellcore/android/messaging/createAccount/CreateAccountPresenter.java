package com.shellcore.android.messaging.createAccount;

import com.shellcore.android.messaging.createAccount.events.CreateAccountEvent;
import com.shellcore.android.messaging.events.LoginEvent;

/**
 * Created by Cesar on 15/02/2017.
 */

public interface CreateAccountPresenter {
    void onCreate();
    void onDestroy();
    void registerNewUser(String email, String password);
    void onEventMainThread(CreateAccountEvent event);
}
