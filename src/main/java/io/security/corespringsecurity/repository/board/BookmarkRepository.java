package io.security.corespringsecurity.repository.board;

import com.google.common.base.Optional;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("SELECT b FROM Bookmark b WHERE b.account = :account AND b.board = :board")
    Bookmark findByAccountAndBoard(@Param("account") Account account, @Param("board") Board board);

    @Query("SELECT b FROM Bookmark b WHERE b.account.id = :userId")
    List<Bookmark> getBookmarksByUserId(@Param("userId") Long userId);

}
