package org.notification.repository;

import org.notification.entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, String>, JpaSpecificationExecutor<Notifications> {

    @Query("SELECT n from Notifications n WHERE n.userId = :userId AND n.createdDate > :oneHourAgo"
    )
    List<Notifications> findByUserIdForLastHour(@Param("userId")String userId, @Param("oneHourAgo") LocalDateTime oneHourAgo);
}
