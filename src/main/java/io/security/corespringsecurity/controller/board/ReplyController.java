package io.security.corespringsecurity.controller.board;

import io.security.corespringsecurity.domain.dto.board.ReplyDto;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Comment;
import io.security.corespringsecurity.domain.entity.board.Reply;
import io.security.corespringsecurity.service.board.BoardService;
import io.security.corespringsecurity.service.board.CommentService;
import io.security.corespringsecurity.service.board.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ReplyController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private BoardService boardService;


    @PostMapping("/members/boards/{boardId}/comments/{commentId}/replies")
    public String createForm(Principal principal, @PathVariable Long boardId, @PathVariable Long commentId, @Valid @ModelAttribute ReplyDto form) {
        Account account = null;
        Reply reply = new Reply();
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
            reply.setAccount(account);
        }

        Optional<Comment> optionalComment = commentService.findById(commentId);
        Comment comment = optionalComment.orElse(null);

        reply.setComment(comment);
        reply.setContent(form.getComment());


        // 여기서 boardId를 사용하여 해당 게시물을 식별하여 댓글을 저장합니다.
        replyService.savePost(reply);

        // 댓글이 속한 게시물의 세부 페이지로 리다이렉트합니다.
        return "redirect:/boards/" + boardId + "/detail";
    }

}
