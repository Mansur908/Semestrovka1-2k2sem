package ru.itis.demo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.demo.security.details.UserDetailsImpl;

@Controller
public class SupportController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/support")
    public String getIndexPage(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        if (user.getRole().equals("ADMIN")){
            return "redirect:/products";
        }
        model.addAttribute("userid", user.getId());
        return "support";
    }
}