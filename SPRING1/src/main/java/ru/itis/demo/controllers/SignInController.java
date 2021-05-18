package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.PermitAll;

@Controller
@RequiredArgsConstructor
public class SignInController {

    @PermitAll
    @GetMapping("/signin")
    public String getSignInPage() {
        return "sign_in_page";
    }

}
