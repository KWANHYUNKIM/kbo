package io.security.corespringsecurity.service.board;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Comment;
import io.security.corespringsecurity.repository.board.CommentRepository;

import java.util.List;

public interface CommentService {

    void createComment(Comment comment);

    List<Comment> findCommentsByBoardId(Long boardId);
}
