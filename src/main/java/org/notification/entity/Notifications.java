package org.notification.entity;

import org.notification.enums.ChannelsEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notifications {
    
    @Id
    @Column(name = "uuid", unique = true, nullable = false)
    private String uuid;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "country")
    private String country;

    @Column(name = "message")
    private String message;

    @Column(name = "schedule_at")
    private LocalDateTime scheduleAt;

    @Column(name = "status")
    private String status;

    @Column(name ="created_date")
    private LocalDateTime createdDate;

    @Column(name = "channels",columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    private ChannelsEnum channels;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public ChannelsEnum getChannels() {
        return channels;
    }

    public void setChannels(ChannelsEnum channels) {
        this.channels = channels;
    }

    public LocalDateTime getScheduleAt() {
        return scheduleAt;
    }

    public void setScheduleAt(LocalDateTime scheduleAt) {
        this.scheduleAt = scheduleAt;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
