package org.notification.service;

import org.notification.queue.Message;
import org.notification.queue.NotificationMessage;

public interface NotificationDeliveryServiceHandler {
    void sendNotification(Message<NotificationMessage> message);
}
