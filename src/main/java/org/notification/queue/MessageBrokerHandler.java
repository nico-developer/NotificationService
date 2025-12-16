package org.notification.queue;

import org.notification.enums.NotificationStatusEnum;

public class MessageBrokerHandler {
    private static NotificationQueueBroker queueBroker;

    static{
        queueBroker = new NotificationQueueBroker<>();
    }

    public static MessageBroker getNotification(NotificationStatusEnum status){
        if(status.equals(NotificationStatusEnum.QUEUE)){
            return queueBroker;
        }
        return null;
    }


}
