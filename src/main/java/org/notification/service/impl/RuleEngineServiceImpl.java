package org.notification.service.impl;

import org.notification.queue.Message;
import org.notification.service.NotificationDeliveryServiceHandler;
import org.notification.service.RuleEngineService;
import org.notification.service.rules.Rules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RuleEngineServiceImpl implements RuleEngineService {

    private final Logger logger = LoggerFactory.getLogger(RuleEngineServiceImpl.class);
    private final List<Rules> rulesList;
    private final NotificationDeliveryServiceHandler notificationDeliveryServiceHandler;

    public RuleEngineServiceImpl(List<Rules> rulesList, NotificationDeliveryServiceHandler notificationDeliveryServiceHandler){
        this.rulesList = rulesList;
        this.notificationDeliveryServiceHandler = notificationDeliveryServiceHandler;
    }
    @Async
    @Override
    public void process(Message message) {
        for(Rules rules: rulesList){
            if(rules.matches(message)){
                rules.apply(message);
            }
            if(!message.isValid()) {
                break;
            }
        }
        if(message.isValid()){
            notificationDeliveryServiceHandler.sendNotification(message);
        }else {
            logger.info("the message is invalid.");
        }
    }
}
