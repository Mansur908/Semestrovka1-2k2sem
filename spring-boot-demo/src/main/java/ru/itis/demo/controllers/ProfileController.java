package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.demo.repositories.CookieRepository;
import ru.itis.demo.repositories.FileRepository;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.services.interfaces.CookieService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final CookieService cookieService;
    private final CookieRepository cookieRepository;
    private final UserRepository userRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String getSignInPage(@CookieValue(value = "AuthCookie", required = false) String cookieValue,
                                Model model){
        if (cookieService.checkCookie(cookieValue)) {
            model.addAttribute("message",cookieRepository.findByUuid(cookieValue).get().getUser().getUsername());
            model.addAttribute("image",cookieRepository.findByUuid(cookieValue).get().getUser().getImageName());
            return "profile_page";
        }
        else {
            return "redirect:/signin";
        }
    }

    @PostMapping
    public String addImage(@CookieValue(value = "AuthCookie", required = false) String cookieValue,
                            @RequestParam("file") MultipartFile file,
                            Model model) throws IOException {
        if (file != null){
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            userRepository.addImageNameByUserId(cookieRepository.findByUuid(cookieValue).get().getUser().getId(),resultFileName);

            model.addAttribute("message",cookieRepository.findByUuid(cookieValue).get().getUser().getUsername());
            model.addAttribute("image",resultFileName);
            return "profile_page";
        }
        return "redirect:/profile";
    }
}