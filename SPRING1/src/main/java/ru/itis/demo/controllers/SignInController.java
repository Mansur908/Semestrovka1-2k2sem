package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.demo.repositories.SupportRepositiry;

import javax.annotation.security.PermitAll;

@Controller
public class SignInController {

//    @Autowired
//    private SupportRepositiry supportRepositiry;

    @PermitAll
    @GetMapping("/signin")
    public String getSignInPage() {
//        System.out.println(supportRepositiry.findAll());
        return "sign_in_page";
    }
}
