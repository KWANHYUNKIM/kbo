package io.security.corespringsecurity.repository.profile;

import io.security.corespringsecurity.domain.entity.profile.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT n FROM Notification n WHERE n.account.id = :accountId")
    List<Notification> findByAccountId(@Param("accountId") Long accountId);    // Additional query methods if needed

    @Query("SELECT COUNT(n) FROM Notification n WHERE n.account.id = :accountId AND n.check = FALSE")
    int countFalseNotifications(@Param("accountId") Long accountId);

    @Modifying
    @Query("UPDATE Notification n SET n.check = true WHERE n.account.id = :accountId")
    void updateAllNotificationsToTrue(@Param("accountId") Long accountId);
}
