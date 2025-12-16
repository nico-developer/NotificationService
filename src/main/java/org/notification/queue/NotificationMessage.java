package org.notification.queue;

import org.notification.enums.ChannelsEnum;

import java.time.LocalDate;

public class NotificationMessage {
    private String userId;
    private String country;
    private ChannelsEnum channel;
    private String message;
    private LocalDate scheduleAt;
    private LocalDate createdDate;
    private String uuid;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ChannelsEnum getChannel() {
        return channel;
    }

    public void setChannel(ChannelsEnum channel) {
        this.channel = channel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getScheduleAt() {
        return scheduleAt;
    }

    public void setScheduleAt(LocalDate scheduleAt) {
        this.scheduleAt = scheduleAt;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
