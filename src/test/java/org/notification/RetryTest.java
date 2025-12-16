package org.notification;

import org.junit.jupiter.api.Test;
import org.notification.entity.Notifications;
import org.notification.enums.ChannelsEnum;
import org.notification.enums.NotificationStatusEnum;
import org.notification.queue.Message;
import org.notification.queue.NotificationMessage;
import org.notification.repository.NotificationsRepository;
import org.notification.service.impl.SendSmsNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RetryTest {

    @Autowired
    @Qualifier("SendSmsNotification")
    private SendSmsNotificationService service;

    @Autowired
    private NotificationsRepository repository;
    @Test
    public void testRetry(){
        Message<NotificationMessage> message = TestUtils.mockMessage();
        message.getPayload().setChannel(ChannelsEnum.SMS);
        String uuid = message.getId();
        service.send(message);
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
