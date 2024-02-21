package io.security.corespringsecurity.service.profile;

import io.security.corespringsecurity.domain.entity.profile.Notification;

import java.util.List;

// NotificationService.java
public interface NotificationService {
    void saveNotification(Notification notification);
    List<Notification> findByAccountId(Long accountId);

    int countFalseNotifications(Long accountId);

    void updateAllNotificationsToTrue(Long accountId);

}
