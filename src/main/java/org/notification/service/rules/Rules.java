package org.notification.service.rules;

import org.notification.queue.Message;
import org.notification.queue.NotificationMessage;

public interface Rules {
    String getRulename();

    boolean matches(Message<NotificationMessage> message);

    void apply(Message<NotificationMessage> message);
}
