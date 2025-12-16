package org.notification.enums;

public enum NotificationStatusEnum {
    QUEUE("QUEUE"),
    SENT("SENT"),
    FAILED("FAILED"),
    RETRY("RETRY");

    private String status;

    private NotificationStatusEnum(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public String toString(){
        return getStatus();
    }
}
