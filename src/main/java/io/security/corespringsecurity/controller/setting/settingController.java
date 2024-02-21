package io.security.corespringsecurity.controller.setting;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Bookmark;
import io.security.corespringsecurity.domain.entity.board.Comment;
import io.security.corespringsecurity.domain.entity.profile.ImageEntity;
import io.security.corespringsecurity.service.UserService;
import io.security.corespringsecurity.service.board.BookmarkService;
import io.security.corespringsecurity.service.board.CommentService;
import io.security.corespringsecurity.service.board.ImageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class settingController {

    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private BookmarkService bookmarkService;
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/settings/profile")
    public String getSettingProfile(Model model, @AuthenticationPrincipal Account account) {
        List<ImageEntity> images = imageService.findAll();

        model.addAttribute("images", images);
        model.addAttribute("account", account);

        return "settings/settingProfile";
    }

    @PostMapping(value = "/settings/profile")
    public String handleSettingProfile(@ModelAttribute Account updatedAccount, @AuthenticationPrincipal Account account ,
                                       @RequestParam("selectedImagePath") String selectedImagePath) {
        updatedAccount.setFilepath(selectedImagePath);
        userService.editByUser(account.getId(),updatedAccount);
        // 현재 사용자의 인증 정보 업데이트

        return "redirect:/settings/profile";
    }

    @GetMapping(value = "/settings/account")
    public String getSettingAccount(Model model, @AuthenticationPrincipal Account account){

        model.addAttribute("account",account);

        return "settings/settingAccount";
    }

    // 활동
    @GetMapping(value = "/users/{userId}/activity")
    public String getSettingActivity(@PathVariable Long userId, Model model) {
        List<Account> accounts = userService.findByUser(userId);
        List<Comment> comments = commentService.findByUserId(userId);
        // 모델에 bookmark 목록을 추가
        model.addAttribute("account", accounts);
        model.addAttribute("comments",comments);
        return "settings/settingActivity";
    }

    @GetMapping(value = "/users/{userId}/questions")
    public String getSettingQuestions(@PathVariable Long userId, Model model) {
        List<Account> accounts = userService.findByUser(userId);
        List<Comment> comments = commentService.findByUserId(userId);
        // 모델에 bookmark 목록을 추가
        model.addAttribute("account", accounts);
        model.addAttribute("comments",comments);
        return "settings/settingActivity";
    }

    @GetMapping(value = "/users/{userId}/articles")
    public String getSettingArticles(@PathVariable Long userId, Model model) {
        List<Account> accounts = userService.findByUser(userId);
        List<Comment> comments = commentService.findByUserId(userId);
        // 모델에 bookmark 목록을 추가
        model.addAttribute("account", accounts);
        model.addAttribute("comments",comments);
        return "settings/settingActivity";
    }

    @GetMapping(value = "/users/{userId}/scraped")
    public String getSettingBookMark( @PathVariable Long userId, Model model){
        List<Account> accounts = userService.findByUser(userId);
        List<Bookmark> bookmarks = bookmarkService.getBookmarksByUserId(userId);
        model.addAttribute("account", accounts);
        model.addAttribute("bookmarks", bookmarks);

        return "settings/settingActivity";
    }
}
