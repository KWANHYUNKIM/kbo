package io.security.corespringsecurity.controller.board;

import io.security.corespringsecurity.domain.dto.board.BoardDto;
import io.security.corespringsecurity.domain.dto.board.CommentDto;
import io.security.corespringsecurity.domain.dto.board.ReplyDto;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.*;
import io.security.corespringsecurity.domain.entity.profile.Notification;
import io.security.corespringsecurity.service.board.*;
import io.security.corespringsecurity.service.profile.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping(value = "/members/board")
    public String boardForm(@ModelAttribute("boardForm") BoardDto form) {
        return "boards/createBoardForm";
    }

    // @Valid , BindingResult 위치 확인 * @Valid 바로 뒤에 나와야함.

    @PostMapping(value = "/members/board")
    public String create(@Valid @ModelAttribute("boardForm") BoardDto form, BindingResult result, Model model , @AuthenticationPrincipal Principal principal){

        if (result.hasErrors()) {
            return "boards/createBoardForm";
        }

        Account account = null;
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        }

        Board board = new Board();
        String categoryName = form.getCategory();
        board.setAccount(account);

        Category category = categoryService.findByCategoryName(categoryName);

        if(category.getCategoryName().equals("STORY")){
            board.setTitle("[사는 얘기] " +  form.getTitle());
        }
        else if(category.getCategoryName().equals("GATHERING")){
            board.setTitle("[모임] " + form.getTitle());
        }


        board.setContent(form.getContent());

        board.setCategory(category);
        boardService.createBoard(board);

        return "redirect:/";
    }

    @GetMapping(value = "/members/board/article")
    public String articleForm(@ModelAttribute("boardForm") BoardDto form) {
        return "boards/createArticleForm";
    }

    @PostMapping(value = "/members/board/article")
    public String createArticle(@Valid @ModelAttribute("boardForm") BoardDto form, BindingResult result, Model model , @AuthenticationPrincipal Principal principal){

        if (result.hasErrors()) {
            return "boards/createArticleForm";
        }

        Account account = null;
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        }

        Board board = new Board();
        String categoryName = form.getCategory();
        board.setAccount(account);
        Category category = categoryService.findByCategoryName(categoryName);

        if(category.getCategoryName().equals("ARTICLE")){
            board.setTitle("[오피셜] " +  form.getTitle());
        }
        else if(category.getCategoryName().equals("NEWS")){
            board.setTitle("[소식] " + form.getTitle());
        }
        else if(category.getCategoryName().equals("BLOG")){
            board.setTitle("[찌라시] " + form.getTitle());
        }

        board.setContent(form.getContent());
        board.setCategory(category);
        boardService.createBoard(board);

        return "redirect:/";
    }

    @GetMapping(value = "/boards/{boardId}/detail")
    public String detailList(Principal principal,Model model, @PathVariable("boardId") Long boardId,
                             @ModelAttribute("commentForm") CommentDto form,
                             @AuthenticationPrincipal Authentication authentication,
                             @ModelAttribute("postForm") ReplyDto postForm) {

        Account account = null;
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        }
        model.addAttribute("account",account);

        List<Board> boards = boardService.findByboardId(boardId);
        List<Comment> comments = commentService.findCommentsByBoardId(boardId);
        List<Like> likes = likeService.findByBoard(boardId);

        if (likes.isEmpty()) {
            likes = null; // 또는 빈 리스트로 유지할 수도 있습니다.
        }

        System.out.println("like 값은? " + likes);
        // 조회수 증가 로직 추가
        boardService.incrementViewCount(boardId);

        // 답글도 가져오기
        for (Comment comment : comments) {
            List<Reply> replies = commentService.findByReply(comment.getId());
            comment.setReplies(replies);
        }




        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() && !(authentication
                instanceof AnonymousAuthenticationToken);
//        System.out.println("LIKES 값 확인 " + likes);

        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("likes", likes );
        model.addAttribute("boards", boards);
        model.addAttribute("commentForm", new CommentDto());
        model.addAttribute("postForm", new ReplyDto());
        model.addAttribute("comments", comments);
        return "boards/detailBoardList";

    }

    @PostMapping("/members/boards/{boardId}/detail")
    public String createForm(Principal principal, @PathVariable Long boardId, @Valid @ModelAttribute CommentDto form) {
        Account account = null;
        Comment comment = new Comment();

        if (principal instanceof UsernamePasswordAuthenticationToken) {
            account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
            comment.setAccount(account);
        }
        Board board = boardService.findByComment(boardId);  // 해당 boardId에 대한 게시물을 가져옴


        comment.setComment(form.getComment());
        comment.setBoard(board);  // Comment 엔티티의 board 속성에 Board 객체를 설정



        // 여기서 boardId를 사용하여 해당 게시물을 식별하여 댓글을 저장합니다.
        commentService.createComment(comment);

        // 댓글을 남기면 노트가 된다.
        Notification notification = new Notification();
        notification.setMessage(account.getUsername() + "님이 댓글을 달아주었습니다");
        notification.setBoard(board);
        notification.setComment(comment);
        notification.setCheck(false);
        notification.setAccount(board.getAccount());
        notificationService.saveNotification(notification);

        // 댓글이 속한 게시물의 세부 페이지로 리다이렉트합니다.
        return "redirect:/boards/" + boardId + "/detail";
    }

    // Todo - article 분류
    @GetMapping(value = "/boards/article/all")
    public String articleList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        List<String> categoryNames = Arrays.asList("ARTICLE", "NEWS", "BLOG");

        List<Category> category = categoryService.findByCategoryNames(categoryNames);
        Page<Board> paging = this.boardService.getListAll(page,category);

        model.addAttribute("paging", paging);

        return "boards/articleList";
    }
    // 전체
    @GetMapping(value = "/boards/all")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        List<String> categoryNames = Arrays.asList("GATHERING", "STORY");

        List<Category> categories = categoryService.findByCategoryNames(categoryNames);
        Page<Board> allPagingResults = boardService.getListAll(page, categories);

        model.addAttribute("paging", allPagingResults);

        return "boards/boardList";
    }
    // 사는 얘기
    @GetMapping(value = "/boards/life/all")
    public String lifeList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Category category = categoryService.findByCategoryName("STORY");
        Page<Board> paging = this.boardService.getList(page,category);

        model.addAttribute("paging", paging);

        return "boards/lifeList";
    }
    // 모임
    @GetMapping(value = "/boards/gathering/all")
    public String gatheringList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Category category = categoryService.findByCategoryName("GATHERING");
        Page<Board> paging = this.boardService.getList(page,category);

        model.addAttribute("paging", paging);

        return "boards/gatheringList";
    }

    @GetMapping("/boards/search")
    public String find(@RequestParam("query") String query, Model model) {

        List<Board> boards = boardService.findByQuery(query);
        model.addAttribute("boards", boards);
        return "boards/boardList";
    }

    /**
     * 게시판 삭제
     **/
    @GetMapping("/boards/delete/{boardId}") // @GetMapping : 이 url 에서 반응 해서 boards/boardsList 넣어 준다.
    public String deleteBoard(@PathVariable("boardId") Long boardId) {
        boardService.deleteBoard(boardId);
        return "redirect:/boards/all";
    }

    /**
     * 수정 폼 ( 게시글 수정 )
     */
    @GetMapping("/boards/edit/{boardId}")
    public String showEditForm(@PathVariable("boardId") Long boardId, Model model) {
        Optional<Board> boards = boardService.findById(boardId);
        Board board = boards.orElse(new Board());
        model.addAttribute("board", board);
        return "boards/editForm"; // 수정 폼의 Thymeleaf 템플릿 이름
    }

    /**
     * 게시판 수정
     */

    @PostMapping("/boards/edit/{boardId}")
    public String handleEditForm(@PathVariable("boardId") Long boardId, @ModelAttribute Board updatedBoard) {
        boardService.editByBoard(boardId, updatedBoard);
        return "redirect:/members/all";
    }
}