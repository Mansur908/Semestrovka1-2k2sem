package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.security.details.UserDetailsImpl;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final UserRepository userRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("message", user.getName());
        model.addAttribute("image", userRepository.findByEmail(user.getUsername()).get().getImage());
        return "profile_page";
    }

    @PostMapping("/profile")
    public String addImage(@AuthenticationPrincipal UserDetailsImpl user,
                           @RequestParam("file") MultipartFile file,
                           Model model) throws IOException {
        System.out.println(file);
        if (file != null){
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            userRepository.addImageNameByUserId(user.getId(),resultFileName);

//            return "redirect:/profile";
            model.addAttribute("message",user.getName());
            model.addAttribute("image",resultFileName);
            return "profile_page";
        }
        return "redirect:/profile";
    }
}