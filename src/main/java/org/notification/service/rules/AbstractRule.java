package org.notification.service.rules;

import org.notification.enums.NotificationStatusEnum;
import org.notification.queue.Message;
import org.notification.queue.MessageBrokerHandler;
import org.notification.queue.NotificationMessage;
import org.notification.queue.NotificationQueueBroker;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractRule implements Rules{

    private void resendToQueue(Message<NotificationMessage> message) {
        NotificationQueueBroker broker = (NotificationQueueBroker) MessageBrokerHandler.getNotification(NotificationStatusEnum.QUEUE);
        broker.publish(message);
    }

}
