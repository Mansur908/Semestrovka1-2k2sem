package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.security.details.UserDetailsImpl;
import ru.itis.demo.services.intrfases.UsersService;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final UsersService usersService;

    //    @PreAuthorize("isAuthenticated()")
//    @PreAuthorize("#userRepository.findByEmail(user.username).get().role == user.role")
//    @PreAuthorize("#user != null && (user.role == \"USER\" || user.role == \"ADMIN\")")
    @PreAuthorize("#user != null ")
    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("message", user.getName());
        model.addAttribute("image", usersService.getUser(user.getUsername()).getImage());
        return "profile_page";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile")
    public String addImage(@AuthenticationPrincipal UserDetailsImpl user,
                           @RequestParam("file") MultipartFile file) throws IOException {
        usersService.addImage(user, file);
        return "redirect:/profile";
    }
}