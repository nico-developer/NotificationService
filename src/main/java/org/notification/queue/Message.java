package org.notification.queue;

import java.time.LocalDateTime;
import java.util.UUID;

// A generic message container
public class Message<T> {
    private final String id;
    private final T payload;
    private final LocalDateTime timestamp;
    private boolean isValid;

    public Message(T payload) {
        this.id = UUID.randomUUID().toString();
        this.payload = payload;
        this.timestamp = LocalDateTime.now();
    }

    public T getPayload() { return payload; }

    @Override
    public String toString() {
        return String.format("[%s] Message ID: %s | Payload: %s", timestamp, id, payload);
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getId(){
        return id;
    }
}