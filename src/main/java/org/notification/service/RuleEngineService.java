package org.notification.service;

import org.notification.queue.Message;

public interface RuleEngineService {
    void process(Message message);
}
