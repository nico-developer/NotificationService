package org.notification.repository;

import org.notification.entity.Notifications;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NotificationSpecifications {
    public static Specification<Notifications> getByUserId(String userId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("userId"), userId);
    }

    public static Specification<Notifications> getLastHour(LocalDateTime date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("createdDate"), date);
    }
}
