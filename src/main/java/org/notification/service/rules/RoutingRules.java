package org.notification.service.rules;

import org.notification.queue.Message;
import org.notification.queue.NotificationMessage;
import org.springframework.stereotype.Component;

@Component
public class RoutingRules implements Rules{
    private final String RULE_NAME = "RoutingRules";
    @Override
    public String getRulename() {
        return RULE_NAME;
    }

    @Override
    public boolean matches(Message<NotificationMessage> message) {
        return message.isValid();
    }

    @Override
    public void apply(Message<NotificationMessage> message) {

    }
}
