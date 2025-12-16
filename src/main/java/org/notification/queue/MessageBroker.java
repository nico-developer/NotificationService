package org.notification.queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

abstract class MessageBroker<T> {
    private final static Logger logger = LoggerFactory.getLogger(MessageBroker.class);
    // LinkedBlockingQueue is thread-safe and blocking
    // (waits if queue is empty/full)
    protected final Queue<Message<T>> queue;

    public MessageBroker() {
        this.queue = new LinkedList<>();
    }

    // Consumer calls this to retrieve a message
    public Message<T> consume() {
        // take() blocks if the queue is empty until a message arrives
        return queue.poll();

    }
}
