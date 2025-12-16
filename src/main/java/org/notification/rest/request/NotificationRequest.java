package org.notification.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class NotificationRequest {

    @JsonProperty("user_id")
    private String userId;

    private String channel;

    private String message;

    private String country;

    @JsonProperty("schedule_at")
    private LocalDate scheduleAt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getScheduleAt() {
        return scheduleAt;
    }

    public void setScheduleAt(LocalDate scheduleAt) {
        this.scheduleAt = scheduleAt;
    }
}
