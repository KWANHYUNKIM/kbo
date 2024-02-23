package io.security.corespringsecurity.service.impl.board;

import com.google.common.base.Optional;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Bookmark;
import io.security.corespringsecurity.repository.board.BookmarkRepository;
import io.security.corespringsecurity.service.board.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;


    @Override
    public void addBookmark(Account account, Board board) {
        Bookmark bookmark = bookmarkRepository.findByAccountAndBoard(account, board);

        if(bookmark != null){
            bookmarkRepository.delete(bookmark);
        }
        else{
            Bookmark book = new Bookmark();
            book.setAccount(account);
            book.setBoard(board);
            bookmarkRepository.save(book);
        }

    }

    @Override
    public void removeBookmark(Account account, Board board) {
        Bookmark bookmark = bookmarkRepository.findByAccountAndBoard(account, board);
        bookmarkRepository.delete(bookmark);
    }

    @Override
    public List<Bookmark> getBookmarksByUserId(Long userId) {
        return bookmarkRepository.getBookmarksByUserId(userId);
    }
}
