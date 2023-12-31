package io.security.corespringsecurity.controller.global;

import io.security.corespringsecurity.domain.entity.Account;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute("currentUser")
    public Account addCurrentUser(@AuthenticationPrincipal Account account) {
        return account;
    }
}
