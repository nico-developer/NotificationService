package org.notification.service;

import org.notification.entity.Notifications;
import org.notification.queue.NotificationMessage;

public interface NotificationRequestService {
    boolean createNotificationQueue(NotificationMessage message);

    Notifications getNotification(String uuid);
}
