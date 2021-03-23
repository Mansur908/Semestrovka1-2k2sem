package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.demo.dto.UserForm;
import ru.itis.demo.services.intrfases.SignUpService;


import javax.annotation.security.PermitAll;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @PermitAll
    @GetMapping("/signup")
    public String getSignUpPage() {
        return "sign_up_page";
    }

    @PermitAll
    @PostMapping("/signup")
    public String signUp(UserForm form, Model model) {

        String str = signUpService.signUp(form);
        if (str.equals("Вы зарегистрировались"))
        return "redirect:/signin";
        else {
            model.addAttribute("message",str);
            return "sign_up_page";
        }
    }
}
