package io.security.corespringsecurity.service.board;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Bookmark;

import java.util.List;

public interface BookmarkService {
    void addBookmark(Account account, Board board);
    void removeBookmark(Account account, Board board);

    List<Bookmark> getBookmarksByUserId(Long userId);
}
