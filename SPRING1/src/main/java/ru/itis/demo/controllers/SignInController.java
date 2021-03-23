package ru.itis.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.PermitAll;

@Controller
public class SignInController {
    @PermitAll
    @GetMapping("/signin")
    public String getSignInPage() {
        return "sign_in_page";
    }
}
