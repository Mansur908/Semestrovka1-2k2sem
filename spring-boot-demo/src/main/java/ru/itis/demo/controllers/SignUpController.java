package ru.itis.demo.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.demo.dto.UserForm;
import ru.itis.demo.services.SignUpService;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "sign_up_page";
    }

    @PostMapping("/signup")
    public String SignUp(UserForm form, Model model){
        if(signUpService.signUp(form)) {
            model.addAttribute("message","Вы зарегистрировались");
        }
        else {
            model.addAttribute("message","Аккаунт уже существует");
        }
        return "sign_up_page";
    }
}