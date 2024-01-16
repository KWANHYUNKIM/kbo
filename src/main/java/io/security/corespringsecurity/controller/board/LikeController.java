package io.security.corespringsecurity.controller.board;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Like;
import io.security.corespringsecurity.service.board.BoardService;
import io.security.corespringsecurity.service.board.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/toggle-like/{boardId}")
    @ResponseBody
    public void toggleLike(@PathVariable Long boardId, Principal principal) {
        toggleLikeUnlike(boardId, principal, true);
    }

    @PostMapping("/toggle-unlike/{boardId}")
    @ResponseBody
    public void toggleUnlike(@PathVariable Long boardId, Principal principal) {
        toggleLikeUnlike(boardId, principal, false);
    }

    private void toggleLikeUnlike(Long boardId, Principal principal, boolean isLike) {
        Account account = null;

        if (principal instanceof UsernamePasswordAuthenticationToken) {
            account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        }
        Board board = boardService.findByComment(boardId);


        Like existingLike = likeService.existsByBoard(boardId);

        if (existingLike != null) {
            // 이미 해당 게시물에 대한 Like 엔티티가 존재하면 업데이트
            if (isLike) {
                existingLike.setLikes(existingLike.getLikes() + 1);
            } else {
                existingLike.setUnlikes(existingLike.getUnlikes() + 1);
            }
            likeService.save(existingLike);
        } else {
            // 해당 게시물에 대한 Like 엔티티가 없으면 새로 생성하여 저장
            Like newLike = new Like();
            newLike.setAccount(account);
            newLike.setBoard(board);

            if (isLike) {
                newLike.setLikes(1);
            } else {
                newLike.setUnlikes(1);
            }
            likeService.save(newLike);
        }
    }
}
