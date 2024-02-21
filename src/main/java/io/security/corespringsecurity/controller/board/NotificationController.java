package io.security.corespringsecurity.controller.board;

import io.security.corespringsecurity.domain.dto.AccountDto;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Comment;
import io.security.corespringsecurity.domain.entity.board.Like;
import io.security.corespringsecurity.domain.entity.profile.Notification;
import io.security.corespringsecurity.service.board.BoardService;
import io.security.corespringsecurity.service.board.CommentService;
import io.security.corespringsecurity.service.board.LikeService;
import io.security.corespringsecurity.service.profile.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService; // 알림 서비스 추가

    @PostMapping("/notifications")
    public ResponseEntity<String> postNotification(Principal principal) {
        Account account = null;
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        }

        if (account != null) {
            notificationService.updateAllNotificationsToTrue(account.getId());
            return ResponseEntity.ok("Notifications updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
    }
}