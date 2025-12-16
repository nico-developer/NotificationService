package org.notification.service.impl;

import org.notification.queue.Message;
import org.notification.queue.NotificationMessage;
import org.notification.service.NotificationDeliveryServiceHandler;
import org.notification.service.SendNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationDeliveryServiceHandlerImpl implements NotificationDeliveryServiceHandler {
    @Autowired
    private final List<SendNotificationService> sendNotification;

    public NotificationDeliveryServiceHandlerImpl(List<SendNotificationService> sendNotification){
        this.sendNotification = sendNotification;
    }
    @Override
    public void sendNotification(Message<NotificationMessage> message) {
        sendNotification.parallelStream().forEach(s ->{
            if(s.matches(message.getPayload().getChannel())){
                s.send(message);
            }
        });
    }
}
