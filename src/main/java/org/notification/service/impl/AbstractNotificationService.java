package org.notification.service.impl;

import org.notification.enums.NotificationStatusEnum;
import org.notification.queue.Message;
import org.notification.queue.MessageBrokerHandler;
import org.notification.queue.NotificationMessage;
import org.notification.queue.NotificationQueueBroker;
import org.notification.service.SendNotificationService;
import org.notification.util.Utils;

public abstract class AbstractNotificationService implements SendNotificationService {

    protected void resendToQueue(Message<NotificationMessage> message) {
        NotificationQueueBroker broker = (NotificationQueueBroker) MessageBrokerHandler.getNotification(NotificationStatusEnum.QUEUE);
        broker.publish(message);
    }

    protected boolean send(){
        return Utils.successOrNot();
    }
}
