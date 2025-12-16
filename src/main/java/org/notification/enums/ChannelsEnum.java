package org.notification.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ChannelsEnum {
    SMS("SMS"),
    EMAIL("EMAIL"),
    SLACKS("SLACKS");

    private String channel;
    private ChannelsEnum(String channel){
        this.channel = channel;
    }

    public String toString(){
        return channel;
    }
}
