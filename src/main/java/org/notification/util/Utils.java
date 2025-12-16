package org.notification.util;

import org.notification.entity.Notifications;
import org.notification.enums.NotificationStatusEnum;
import org.notification.queue.Message;
import org.notification.queue.NotificationMessage;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class Utils {

    // this is just to simulate error for retry
    public static boolean successOrNot(){
         Random rand = new Random();
         return rand.nextInt(20) >9;
    }


    public static Notifications buildEntity(NotificationMessage message,String uuid, NotificationStatusEnum status) {
        Notifications notifications = new Notifications();
        BeanUtils.copyProperties(message, notifications);
        notifications.setStatus(status.getStatus());
        notifications.setCreatedDate(LocalDateTime.now());
        notifications.setUuid(uuid);
        notifications.setChannels(message.getChannel());
        return notifications;
    }
}
