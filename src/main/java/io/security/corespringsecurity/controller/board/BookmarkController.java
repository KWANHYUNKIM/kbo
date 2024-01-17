package io.security.corespringsecurity.controller.board;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.service.board.BoardService;
import io.security.corespringsecurity.service.board.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {
    @Autowired
    private BookmarkService bookmarkService;
    @Autowired
    private BoardService boardService; // BoardService는 사용자의 게시글을 가져오기 위해 사용

    @Autowired
    public BookmarkController(BookmarkService bookmarkService, BoardService boardService) {
        this.bookmarkService = bookmarkService;
        this.boardService = boardService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBookmark(@RequestParam Long boardId, Principal principal) {
        Account account = null;
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        }

        Board board = boardService.findByComment(boardId);

        if (account != null && board != null) {
            bookmarkService.addBookmark(account, board);
            return ResponseEntity.ok("Bookmark added successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid request.");
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeBookmark(@RequestParam Long boardId, Principal principal) {

        Account account = null;
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        }

        Board board = boardService.findByComment(boardId);

        if (account != null && board != null) {
            bookmarkService.removeBookmark(account, board);
            return ResponseEntity.ok("Bookmark removed successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid request.");
        }
    }
}
