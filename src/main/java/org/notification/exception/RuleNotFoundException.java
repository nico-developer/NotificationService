package org.notification.exception;

public class RuleNotFoundException extends RuntimeException{

    public RuleNotFoundException(String message){
        super(message);
    }
}
