package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.demo.dto.UserForm;
import ru.itis.demo.services.intrfases.SignUpService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;

    @PermitAll
    @GetMapping("/signup")
    public String getSignUpPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "sign_up_page";
    }

    @PermitAll
    @PostMapping("/signup")
    public String signUp(@Valid UserForm form, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            String str = signUpService.signUp(form);
            if (str.equals("Вы зарегистрировались")) {
                return signUpService.sendMail(form);
            } else {
                model.addAttribute("message", str);
                return "sign_up_page";
            }
        } else {
            model.addAttribute("userForm", form);
            return "sign_up_page";
        }
    }
}