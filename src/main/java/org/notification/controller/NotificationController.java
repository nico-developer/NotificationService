package org.notification.controller;

import org.notification.entity.Notifications;
import org.notification.enums.ChannelsEnum;
import org.notification.queue.NotificationMessage;
import org.notification.repository.NotificationsRepository;
import org.notification.rest.request.NotificationRequest;
import org.notification.service.NotificationRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Index Controller.
 */
@RestController
public class NotificationController {
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    final NotificationRequestService notificationRequestService;

    public  NotificationController(NotificationRequestService notificationRequestService){
        this.notificationRequestService = notificationRequestService;
    }
    @PostMapping("/notifications")
    public ResponseEntity<Object> sendNotification(@RequestBody NotificationRequest request) throws IOException {
        NotificationMessage message = new NotificationMessage();
        logger.info("[NotificationController.sendNotification]");
        logger.info("request: {}", request);
        BeanUtils.copyProperties(request, message);
        message.setChannel(ChannelsEnum.valueOf(request.getChannel()));
        message.setCreatedDate(LocalDate.now());

        boolean notificationQueue = notificationRequestService.createNotificationQueue(message);
        return new ResponseEntity<>(notificationQueue, HttpStatus.OK);
    }

    @GetMapping("/notifications/{id}")
    public ResponseEntity<Object> getNotification(@PathVariable("id") String uuid){
        Notifications notifications = notificationRequestService.getNotification(uuid);
        return ResponseEntity.ok(notifications);
    }

}