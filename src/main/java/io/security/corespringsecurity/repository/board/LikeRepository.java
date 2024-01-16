package io.security.corespringsecurity.repository.board;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    @Query("SELECT lr FROM Like lr WHERE lr.board.id = :boardId")
    Like existsByBoard(@Param("boardId") Long boardId);


    @Query("SELECT lr FROM Like lr WHERE lr.board.id = :boardId")
    List<Like> findByLikeAndUnlike(@Param("boardId") Long boardId);
}

