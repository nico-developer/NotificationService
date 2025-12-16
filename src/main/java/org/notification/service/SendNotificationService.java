package org.notification.service;

import org.notification.enums.ChannelsEnum;
import org.notification.queue.Message;
import org.notification.queue.NotificationMessage;

public interface SendNotificationService {
    boolean send(Message<NotificationMessage> message);
    boolean matches(ChannelsEnum channel);
}
