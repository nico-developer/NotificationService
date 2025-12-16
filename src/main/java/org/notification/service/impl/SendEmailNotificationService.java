package org.notification.service.impl;

import org.notification.entity.Notifications;
import org.notification.enums.ChannelsEnum;
import org.notification.enums.NotificationStatusEnum;
import org.notification.queue.Message;
import org.notification.queue.NotificationMessage;
import org.notification.repository.NotificationsRepository;
import org.notification.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("SendEmailNotification")
public class SendEmailNotificationService extends AbstractNotificationService {

    private final Logger LOGGER = LoggerFactory.getLogger(SendEmailNotificationService.class);

    private final NotificationsRepository repository;
    public SendEmailNotificationService(NotificationsRepository notificationsRepository){
        this.repository = notificationsRepository;
    }
    @Override
    public boolean send(Message<NotificationMessage> message) {
        // this is just a Mock service
        LOGGER.info("Sending Email notifications...");
        boolean isSuccess = send();
        Notifications notifications = Utils.buildEntity(message.getPayload(),
                message.getId(), NotificationStatusEnum.SENT);
        if(!isSuccess){
            LOGGER.info("SENDING EMAIL FAILED!");
            notifications.setStatus(NotificationStatusEnum.FAILED.getStatus());
            resendToQueue(message);
        }
        repository.save(notifications);
        return isSuccess;
    }

    @Override
    public boolean matches(ChannelsEnum channel) {
        return channel.equals(ChannelsEnum.EMAIL);
    }
}
