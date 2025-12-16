package org.notification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.notification.entity.Notifications;
import org.notification.enums.NotificationStatusEnum;
import org.notification.queue.Message;
import org.notification.queue.NotificationMessage;
import org.notification.repository.NotificationsRepository;
import org.notification.service.NotificationDeliveryServiceHandler;
import org.notification.service.impl.NotificationDeliveryServiceHandlerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NotificationDeliveryServiceHandlerTest {

    private final Logger logger = LoggerFactory.getLogger(NotificationDeliveryServiceHandlerTest.class);
    @Autowired
    private NotificationDeliveryServiceHandler service;

    @Autowired
    private NotificationsRepository repository;
    @Test
    public void handlerTest(){
        Message<NotificationMessage> message = TestUtils.mockMessage();
        String uuid = message.getId();
        service.sendNotification(message);
        Optional<Notifications> record = getNotification(uuid);
        assertEquals(record.isPresent(), true);
        Notifications notifications = record.get();
        deleteRecord(uuid);
    }

    private Optional<Notifications> getNotification(String uuid){
        return repository.findById(uuid);
    }

    private void deleteRecord(String uuid){
        repository.deleteById(uuid);
    }


}
