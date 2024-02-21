package io.security.corespringsecurity.service.impl.profile;

import io.security.corespringsecurity.domain.entity.profile.Notification;
import io.security.corespringsecurity.repository.profile.NotificationRepository;
import io.security.corespringsecurity.service.profile.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void saveNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> findByAccountId(Long accountId) {
        return notificationRepository.findByAccountId(accountId);
    }

    @Override
    public int countFalseNotifications(Long accountId) {
        return notificationRepository.countFalseNotifications(accountId);
    }

    @Override
    @Transactional
    public void updateAllNotificationsToTrue(Long accountId) {
         notificationRepository.updateAllNotificationsToTrue(accountId);
    }

    // Additional methods if needed
}
