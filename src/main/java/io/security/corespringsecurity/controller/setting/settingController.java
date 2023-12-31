package io.security.corespringsecurity.controller.setting;

import io.security.corespringsecurity.domain.dto.AccountDto;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.ImageEntity;
import io.security.corespringsecurity.domain.entity.board.Board;
import io.security.corespringsecurity.service.UserService;
import io.security.corespringsecurity.service.board.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class settingController {

    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;
    @GetMapping(value = "/settings/profile")
    public String getSettingProfile(Model model, @AuthenticationPrincipal Account account) {
        List<ImageEntity> images = imageService.findAll();

        model.addAttribute("images", images);
        model.addAttribute("account", account);

        return "settings/settingProfile";
    }

    @PostMapping(value = "/settings/profile")
    public String handleSettingProfile(@ModelAttribute Account updatedAccount, @AuthenticationPrincipal Account account,
                                       @RequestParam("selectedImagePath") String selectedImagePath) {
        updatedAccount.setFilepath(selectedImagePath);
        userService.editByUser(account.getId(),updatedAccount);
        return "redirect:/settings/profile";
    }
    @GetMapping(value = "/settings/account")
    public String getSettingAccount(Model model){
        return "settings/settingAccount";
    }


}
