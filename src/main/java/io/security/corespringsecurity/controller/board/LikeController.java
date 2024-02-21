package io.security.corespringsecurity.controller.board;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Like;
import io.security.corespringsecurity.domain.entity.board.ReactionType;
import io.security.corespringsecurity.domain.entity.profile.Notification;
import io.security.corespringsecurity.service.board.BoardService;
import io.security.corespringsecurity.service.board.LikeService;
import io.security.corespringsecurity.service.profile.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
public class LikeController {
    @Autowired
    private LikeService likeService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private NotificationService notificationService;


    @PostMapping("/toggle-like/{boardId}")
    @ResponseBody
    public void toggleLike(@PathVariable Long boardId, @AuthenticationPrincipal Principal principal) {
        toggleReaction(boardId, principal, ReactionType.LIKE);
    }

    @PostMapping("/toggle-angry/{boardId}")
    @ResponseBody
    public void toggleAngry(@PathVariable Long boardId, @AuthenticationPrincipal Principal principal) {
        toggleReaction(boardId, principal, ReactionType.ANGRY);
    }

    @PostMapping("/toggle-fan/{boardId}")
    @ResponseBody
    public void toggleFan(@PathVariable Long boardId, @AuthenticationPrincipal Principal principal) {
        toggleReaction(boardId, principal, ReactionType.FAN);
    }

    @PostMapping("/toggle-follow/{boardId}")
    @ResponseBody
    public void toggleFollow(@PathVariable Long boardId, @AuthenticationPrincipal Principal principal) {
        toggleReaction(boardId, principal, ReactionType.FOLLOW);
    }

    @PostMapping("/toggle-sad/{boardId}")
    @ResponseBody
    public void toggleSad(@PathVariable Long boardId, @AuthenticationPrincipal Principal principal) {
        toggleReaction(boardId, principal, ReactionType.SAD);
    }

    private void toggleReaction(Long boardId, Principal principal, ReactionType reactionType) {
        System.out.println("boardId =" + boardId + "principal =" + principal + "rectionType = " + reactionType);
        Account account = null;
        Notification notification = new Notification();

        if (principal instanceof UsernamePasswordAuthenticationToken) {
            account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        }
        System.out.println("account = " + account);

        Board board = boardService.findByComment(boardId);

        notification.setAccount(board.getAccount()); // 글쓴이
        notification.setBoard(board);

        Like existingReaction = likeService.findByBoardAndType(boardId);
        if (existingReaction != null) {
            // If the reaction exists, update it
            updateReaction(existingReaction, account, notification, reactionType);
        } else {
            // If the reaction doesn't exist, create a new one
            createNewReaction(board, account, notification, reactionType);
        }
    }

    private void updateReaction(Like existingReaction, Account account, Notification notification, ReactionType reactionType) {
        // Update the existing reaction
        switch (reactionType) {
            case LIKE:
                existingReaction.setLikes(existingReaction.getLikes() + 1);
                notification.setMessage(account.getUsername() + "님이 좋아요를 눌렀습니다.");
                break;
            case ANGRY:
                existingReaction.setAngry(existingReaction.getAngry() + 1);
                notification.setMessage(account.getUsername() + "님이 화나요를 눌렀습니다.");

                // Add notification message for angry reaction
                break;
            case FAN:
                existingReaction.setFan(existingReaction.getFan() + 1);
                notification.setMessage(account.getUsername() + "님이 팬이에요를 눌렀습니다.");

                // Add notification message for fan reaction
                break;
            case FOLLOW:
                existingReaction.setFollow(existingReaction.getFollow() + 1);
                notification.setMessage(account.getUsername() + "님이 궁굼해요를 눌렀습니다.");

                // Add notification message for follow reaction
                break;
            case SAD:
                existingReaction.setSad(existingReaction.getSad() + 1);
                notification.setMessage(account.getUsername() + "님이 슬퍼요를 눌렀습니다.");

                // Add notification message for sad reaction
                break;
            // Add more cases if needed for other reaction types
        }

        notification.setCheck(false);
        notificationService.saveNotification(notification);
        likeService.save(existingReaction);
    }

    private void createNewReaction(Board board, Account account, Notification notification, ReactionType reactionType) {
        // Create a new reaction
        Like newReaction = new Like();
        newReaction.setAccount(account);
        newReaction.setBoard(board);
        switch (reactionType) {
            case LIKE:
                newReaction.setLikes(1);
                notification.setMessage(account.getUsername() + "님이 좋아요를 눌렀습니다.");
                break;
            case ANGRY:
                newReaction.setAngry(1);
                notification.setMessage(account.getUsername() + "님이 화나요를 눌렀습니다.");
                // Add notification message for angry reaction
                break;
            case FAN:
                newReaction.setFan(1);
                notification.setMessage(account.getUsername() + "님이 팬이에요를 눌렀습니다.");

                // Add notification message for fan reaction
                break;
            case FOLLOW:
                newReaction.setFollow(1);
                notification.setMessage(account.getUsername() + "님이 궁굼해요를 눌렀습니다.");
                // Add notification message for follow reaction
                break;
            case SAD:
                newReaction.setSad(1);
                // Add notification message for sad reaction
                notification.setMessage(account.getUsername() + "님이 슬퍼요를 눌렀습니다.");

                break;
            // Add more cases if needed for other reaction types
        }
        System.out.println("1.created 내부 board" + board + "account" + account);

        notification.setCheck(false);
        notificationService.saveNotification(notification);

        try {
            likeService.save(newReaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/get-like-count/{boardId}")
    public ResponseEntity<Integer> getLikeCount(@PathVariable Long boardId) {
        int likeCount = likeService.getLikeCount(boardId);
        return ResponseEntity.ok(likeCount);
    }
}
