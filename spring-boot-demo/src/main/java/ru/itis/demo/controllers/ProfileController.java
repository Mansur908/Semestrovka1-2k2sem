package ru.itis.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String getSignInPage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if (session == null){
            return "redirect:/signin";
        }
        else {
            if (getCookieValue(request.getCookies()) != null || session.getAttribute("email") != null)
                model.addAttribute("message",session.getAttribute("email"));
                return "profile_page";
        }
    }

    public static String getCookieValue(Cookie[] a){
        String str = null;
        for (Cookie c : a) {
            if (c.getName().equals("email")) {
                str = c.getValue();
            }
        }
        return str;
    }
}