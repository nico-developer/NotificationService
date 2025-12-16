package org.notification.service.rules;

import org.notification.entity.Notifications;
import org.notification.enums.NotificationStatusEnum;
import org.notification.queue.Message;
import org.notification.queue.NotificationMessage;
import org.notification.repository.NotificationSpecifications;
import org.notification.repository.NotificationsRepository;
import org.notification.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RateLimitingRule implements Rules{
    private final Logger logger = LoggerFactory.getLogger(RateLimitingRule.class);

    StandardEvaluationContext context = null;
    ExpressionParser  parser = new SpelExpressionParser();
    final NotificationsRepository notificationsRepository;
    public RateLimitingRule( NotificationsRepository notificationsRepository){
        this.notificationsRepository = notificationsRepository;
    }
    final String RULE_NAME ="RateLimitingRule";
    @Override
    public String getRulename() {
        return RULE_NAME;
    }

    @Override
    public boolean matches(Message<NotificationMessage> message) {
        logger.info("matches");
        return message.isValid();
    }
    @Override
    public void apply(Message<NotificationMessage> message) {
        List<Notifications> userQueueLastHour = getUserNotifications(message.getPayload());
        logger.info("userQueueLastHour: {}", userQueueLastHour);
        String rules = getRulesFromConfig();
        logger.info("rules: {}", rules);
        logger.info("userQueueLastHour.size() : {}", userQueueLastHour.size());
        context = new StandardEvaluationContext(userQueueLastHour);
        Expression exp = parser.parseExpression(rules);
        boolean isValid = exp.getValue(context, Boolean.class);
        if(!isValid){
            message.setValid(false);
            saveMessageToDB(message);
        }
    }

    private String getRulesFromConfig() {
        List<org.notification.entity.Rules> rulesList = JsonUtils.getRules();
        logger.info("rules from configs size: {}",rulesList.size());
        logger.info("rules {}",rulesList);
        List<org.notification.entity.Rules> rateLimitingRules =
                rulesList.stream().filter(r-> r.getRuleName().equals(getRulename()))
                        .collect(Collectors.toList());
        if(rateLimitingRules.isEmpty()){
           logger.error("rule {} is empty", getRulename());
        }
        return rateLimitingRules.get(0).getRules();
    }

    private List<Notifications> getUserNotifications(NotificationMessage payload) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime oneHourAgo = currentTime.minusHours(1);
        logger.info("oneHourAgo: {}",oneHourAgo);
        logger.info("userId: {}",payload.getUserId());
        Pageable pageable = PageRequest.of(0, 5);
        return notificationsRepository.findAll(buildSpecs(payload.getUserId(),oneHourAgo),pageable).toList();
    }

    private Specification<Notifications> buildSpecs(String userId, LocalDateTime oneHourAgo) {
        return Specification
                .where(NotificationSpecifications.getByUserId(userId)
                .and(NotificationSpecifications.getLastHour(oneHourAgo)));
    }


    private void saveMessageToDB(Message<NotificationMessage> message) {
        notificationsRepository.save(buildNotificationEntity(message));
    }

    private Notifications buildNotificationEntity(Message<NotificationMessage> message) {
        Notifications notifications = new Notifications();
        BeanUtils.copyProperties(message.getPayload(),notifications);
        notifications.setStatus(NotificationStatusEnum.FAILED.getStatus());
        return notifications;
    }
}
