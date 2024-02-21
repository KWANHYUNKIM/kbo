package io.security.corespringsecurity.service.impl.board;

import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Comment;
import io.security.corespringsecurity.domain.entity.board.Reply;
import io.security.corespringsecurity.repository.board.BoardRepository;
import io.security.corespringsecurity.repository.board.CommentRepository;
import io.security.corespringsecurity.service.board.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void createComment(Comment comment){
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findCommentsByBoardId(Long boardId) {
        return commentRepository.findCommentsByBoardId(boardId);
    }

    @Override
    public List<Comment> findByCommentId(Long commentID) {
        return commentRepository.findByCommentId(commentID);
    }
    @Override
    public List<Reply> findByReply (Long commentId){
        return commentRepository.findByReply(commentId);
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public List<Comment> findByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }
}
