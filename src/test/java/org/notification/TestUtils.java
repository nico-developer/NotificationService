package org.notification;

import org.notification.enums.ChannelsEnum;
import org.notification.queue.Message;
import org.notification.queue.NotificationMessage;

import java.time.LocalDate;

public class TestUtils {

    public static Message<NotificationMessage> mockMessage() {
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setChannel(ChannelsEnum.SMS);
        notificationMessage.setMessage("message");
        notificationMessage.setCreatedDate(LocalDate.now());
        notificationMessage.setCountry("Philippines");
        notificationMessage.setScheduleAt(LocalDate.now());
        notificationMessage.setUserId("1234");
        Message<NotificationMessage> message = new Message<>(notificationMessage);
        return message;
    }
}
