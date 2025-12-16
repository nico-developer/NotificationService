package org.notification.service.rules;

import org.notification.queue.Message;
import org.notification.service.dto.ExternalResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExternalRules2Service implements Rules{
    private final Logger logger = LoggerFactory.getLogger(ExternalRules2Service.class);
    final String RULE_NAME = "ExternalRules2Service";
    @Override
    public String getRulename() {
        return RULE_NAME;
    }

    @Override
    public boolean matches(Message message) {
        logger.info("matches");
        // filter if this message should be process by this Rule
        if(message != null){
            return true;
        }
        return false ;
    }

    @Override
    public void apply(Message message) {
        logger.info("apply");
        // this will process the message by a external application
        ResponseEntity<ExternalResponse> response= sendNotificationMessage(message);
    }

    private ResponseEntity<ExternalResponse> sendNotificationMessage(Message message) {
        // send an http request here for external processing
        ResponseEntity<ExternalResponse> response = mockResponse();
        return response;
    }

    private ResponseEntity<ExternalResponse> mockResponse() {
        ExternalResponse externalResponse = new ExternalResponse();
        externalResponse.setSuccess(true);
        return ResponseEntity.ok(externalResponse);
    }
}
