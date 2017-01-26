package com.shellcore.android.messaging.libs;

import java.util.Objects;

/**
 * Created by Cesar on 26/01/2017.
 */

public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
