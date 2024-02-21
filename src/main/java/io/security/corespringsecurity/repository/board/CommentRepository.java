package io.security.corespringsecurity.repository.board;

import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Comment;
import io.security.corespringsecurity.domain.entity.board.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("SELECT b FROM Comment b WHERE b.board.id = :boardId ORDER BY b.createdDate DESC")
    List<Comment> findCommentsByBoardId(@Param("boardId") Long boardId);

    @Query("SELECT c FROM Comment c WHERE c.id = :commentId")
    List<Comment> findByCommentId(@Param("commentId") Long commentId);

    @Query("SELECT r FROM Reply r WHERE r.comment.id = :commentId")
    List<Reply> findByReply(@Param("commentId") Long commentId);


    @Query("SELECT r FROM Comment r WHERE r.account.id = :userId")
    List<Comment> findByUserId(@Param("userId") Long userId);

}