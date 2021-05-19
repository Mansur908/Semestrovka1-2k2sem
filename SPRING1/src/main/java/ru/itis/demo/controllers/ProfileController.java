package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.demo.dto.SubscriptionForm;
import ru.itis.demo.security.details.UserDetailsImpl;
import ru.itis.demo.services.intrfases.UsersService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final UsersService usersService;

//    @PreAuthorize("#user != null")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("message", user.getName());
        model.addAttribute("image", usersService.getUser(user.getUsername()).getImage());
        model.addAttribute("subscription", usersService.getUser(user.getUsername()).isProductSubscription());
        return "profile_page";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile")
    public String addImage(@AuthenticationPrincipal UserDetailsImpl user,
                           @RequestParam("file") MultipartFile file) throws IOException {
        usersService.addImage(user, file);
        return "redirect:/profile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/subscription")
    public String setSubscription(@AuthenticationPrincipal UserDetailsImpl user, SubscriptionForm form) {
        usersService.changeSubscription(user.getId(),form.isSubscription());
        return "redirect:/profile";
    }
}