package ru.itis.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.demo.dto.UserForm;
import ru.itis.demo.services.SignInService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SignInController {

    @Autowired
    private SignInService signInService;

    @GetMapping("/signin")
    public String getSignInPage(){
        return "sign_in_page";
    }

    @PostMapping("/signin")
    public String SignIn(UserForm form, Model model, HttpServletResponse response, HttpServletRequest request){

        switch (signInService.signIn(form)){
            case ("nouser"):
                model.addAttribute("message","Вы не зарегистрированны");
                return "sign_in_page";
            case ("password"):
                model.addAttribute("message","Неправильный пароль");
                return "sign_in_page";
            default:
                Cookie cookie = new Cookie("email",form.getEmail());
                cookie.setMaxAge(1000000);
                response.addCookie(cookie);
                HttpSession session = request.getSession();
                session.setAttribute("email",form.getEmail());
                return "redirect:/profile";
        }
    }
}
