package io.security.corespringsecurity.repository.board;

import io.security.corespringsecurity.domain.entity.board.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

//    @Query("SELECT b FROM Reply b WHERE b.c = :commentId")
//
//    List<Reply> findByCommentId(@Param("commentId") Long commentId);

}