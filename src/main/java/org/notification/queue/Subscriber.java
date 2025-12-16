package org.notification.queue;

import org.springframework.scheduling.annotation.Async;

public interface Subscriber<T> {

    public void onMessage(Message<T> message);
}
