package org.notification.queue;

public interface Publisher<T> {

    void publish(Message<T> message);
    void subscribe(Subscriber<T> subscriber);
}
