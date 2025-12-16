package org.notification.service.impl;

import org.notification.entity.Notifications;
import org.notification.enums.NotificationStatusEnum;
import org.notification.exception.NotificationNotFoundException;
import org.notification.queue.Message;
import org.notification.queue.MessageBrokerHandler;
import org.notification.queue.NotificationMessage;
import org.notification.queue.NotificationQueueBroker;
import org.notification.repository.NotificationsRepository;
import org.notification.service.NotificationRequestService;
import org.notification.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class NotificationRequestServiceImpl implements NotificationRequestService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationRequestServiceImpl.class);

    private final NotificationsRepository notificationsRepository;

    public NotificationRequestServiceImpl(NotificationsRepository notificationsRepository){
        this.notificationsRepository = notificationsRepository;
    }
    @Override
    public boolean createNotificationQueue(NotificationMessage message) {
        logger.info("[NotificationRequestServiceImpl.createNotificationQueue]");
        logger.info("message: {}", message);
        NotificationQueueBroker broker = (NotificationQueueBroker) MessageBrokerHandler.getNotification(NotificationStatusEnum.QUEUE);
        Message<NotificationMessage> brokerMessage = buildMessage(message);

        notificationsRepository.save(
                Utils.buildEntity(message, brokerMessage.getId(),NotificationStatusEnum.QUEUE));
        broker.publish(brokerMessage);
        return true;
    }

    @Override
    public Notifications getNotification(String uuid) {
        Optional<Notifications> notificationsOptional = notificationsRepository.findById(uuid);
        if(notificationsOptional.isEmpty()){
            throw new NotificationNotFoundException("Notifications with id " + uuid+  " not found");
        }
        return notificationsRepository.findById(uuid).get();
    }

    private Message<NotificationMessage> buildMessage(NotificationMessage requestMessage) {
        Message<NotificationMessage> message = new Message<>(requestMessage);
        message.setValid(true);
        message.getPayload().setUuid(message.getId());
        return message;
    }
}
