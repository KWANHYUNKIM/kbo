package io.security.corespringsecurity.controller.board;

import io.security.corespringsecurity.domain.dto.AccountDto;
import io.security.corespringsecurity.domain.dto.board.BoardDto;
import io.security.corespringsecurity.domain.dto.board.CommentDto;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.domain.entity.board.Comment;
import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import io.security.corespringsecurity.service.UserService;
import io.security.corespringsecurity.service.board.BoardService;
import io.security.corespringsecurity.service.board.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;
    @GetMapping(value ="/members/board")
    public String boardForm(@ModelAttribute("boardForm") BoardDto form){
        return "boards/createBoardForm";
    }

    @PostMapping(value ="/members/board")
    public String create(Principal principal, @Valid BoardDto form, MultipartFile file, BindingResult result) throws IOException {
        if(result.hasErrors()) {
            return "boards/createBoardForm";
        }
        // 파일 저장
        String projectPath = System.getProperty("user.dir") + "//src//main//resources//static//images";

        /*식별자 . 랜덤으로 이름 만들어줌*/
        UUID uuid = UUID.randomUUID();

        /*랜덤식별자_원래파일이름 = 저장될 파일이름 지정*/
        String fileName = uuid + "_" + file.getOriginalFilename();

        /*빈 껍데기 생성*/
        /*File을 생성할건데, 이름은 "name" 으로할거고, projectPath 라는 경로에 담긴다는 뜻*/
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        Account account = null;
        Board board = new Board();

        if (principal instanceof UsernamePasswordAuthenticationToken) {
            account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
            board.setAccount(account);
        }
//      System.out.println("FileName =" + fileName);


        board.setTitle(form.getTitle());
        board.setContent(form.getContent());
        board.setFilename(fileName);
        board.setFilepath("/images/" + fileName);

        boardService.createBoard(board);

        return "redirect:/";
    }
    /**
     * 게시판 디테일
     * Todo :
     **/

    @GetMapping(value = "/boards/{boardId}/detail")
    public String detailList(Model model, @PathVariable("boardId") Long boardId,
                             @ModelAttribute("commentForm") CommentDto form){

        List<Board> boards = boardService.findByboardId(boardId);
        List<Comment> comments = commentService.findCommentsByBoardId(boardId);

        // 조회수 증가 로직 추가
        boardService.incrementViewCount(boardId);


        model.addAttribute("boards",boards);
        model.addAttribute("commentForm", new CommentDto());
        model.addAttribute("comments", comments);
        return "boards/detailBoardList";

    }

    @PostMapping("/members/boards/{boardId}/detail")
    public String createForm(Principal principal,@PathVariable Long boardId, @Valid @ModelAttribute CommentDto form) {
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

        // 댓글이 속한 게시물의 세부 페이지로 리다이렉트합니다.
        return "redirect:/boards/" + boardId +"/detail";
    }

    @GetMapping(value = "/boards/all")
    public String list(Model model , @RequestParam(value="page", defaultValue="0") int page) {
        Page<Board> paging = this.boardService.getList(page);
        model.addAttribute("paging", paging);

        return "boards/boardList";
    }

    @GetMapping("/boards/search")
    public String find(@RequestParam("query") String query, Model model){

        List<Board> boards = boardService.findByQuery(query);
        model.addAttribute("boards",boards);
        return "boards/boardList";
    }

    /**
     * 게시판 삭제
     **/
    @GetMapping("/boards/delete/{boardId}") // @GetMapping : 이 url 에서 반응 해서 boards/boardsList 넣어 준다.
    public String deleteBoard(@PathVariable("boardId") Long boardId){
        boardService.deleteBoard(boardId);
        return "redirect:/boards/all";
    }

    /**
     *  수정 폼 ( 게시글 수정 )
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

//    /**
//     * 정렬
//     **/
//    @GetMapping("/boards/sort")
//    public String getBoards(Model model, @RequestParam(name = "sort", defaultValue = "createdDate") String sort) {
//        List<Board> boards = boardService.getAllSortedByBoard(sort);
//        model.addAttribute("boards", boards);
//        model.addAttribute("currentSort", sort);
//        return "boards/boardList";
//    }
}
