package org.notification.service;

import org.notification.enums.NotificationStatusEnum;
import org.notification.queue.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProcessNotificationService implements Subscriber<NotificationMessage> {
    private final static Logger logger = LoggerFactory.getLogger(ProcessNotificationService.class);

    private NotificationQueueBroker broker = null;

    private final RuleEngineService ruleEngineService;

    public ProcessNotificationService(RuleEngineService ruleEngineService){
        this.ruleEngineService = ruleEngineService;
    }
    @PostConstruct
    public void init(){
        broker = (NotificationQueueBroker) MessageBrokerHandler.getNotification(NotificationStatusEnum.QUEUE);
        if(broker != null)
            broker.subscribe(this);
    }

    @Async
    @Override
    public void onMessage(Message<NotificationMessage> message) {
        logger.info("consumed message: {}", message);
        logger.info("running the rule engine");
        ruleEngineService.process(message);
    }
}
