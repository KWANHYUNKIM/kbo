package io.security.corespringsecurity.service.board;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Comment;
import io.security.corespringsecurity.domain.entity.board.Reply;
import io.security.corespringsecurity.repository.board.CommentRepository;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    void createComment(Comment comment);

    List<Comment> findCommentsByBoardId(Long boardId);

    List<Comment>  findByCommentId(Long commentID);

    List<Reply> findByReply(Long commentID);

    Optional<Comment> findById (Long commentId);
    List<Comment> findByUserId(Long userId);

}
