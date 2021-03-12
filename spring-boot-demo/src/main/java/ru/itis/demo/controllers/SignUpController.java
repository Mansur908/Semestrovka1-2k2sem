package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.demo.dto.SignUpForm;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.services.interfaces.CookieService;
import ru.itis.demo.services.interfaces.MailService;
import ru.itis.demo.services.interfaces.SignUpService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final CookieService cookieService;
    private final SignUpService signUpService;
    private final MailService mailService;

    @Value("${auth.user.redirect.url}")
    private String authUserRedirectUrl;

    @GetMapping
    public String getSignUpPage(@CookieValue(value = "AuthCookie", required = false) String cookieValue,
                                @RequestParam(value = "error", required = false) String error,
                                Model model) {
        if (cookieService.checkCookie(cookieValue)) {
            return authUserRedirectUrl;
        }
        model.addAttribute("error", error);
        return "sign_up_page";
    }

    @PostMapping
    public String signUp(@CookieValue(value = "AuthCookie", required = false) String cookieValue,
                         @Valid SignUpForm form,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (cookieService.checkCookie(cookieValue)) {
            return authUserRedirectUrl;
        }
        if (bindingResult.hasErrors()) {
            List<String> errorsList = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            redirectAttributes.addAttribute("error", errorsList.toString());
            return "redirect:/signup";
        }
        UserDto userDto = null;
        if ((userDto = signUpService.signUp(form)) != null) {
            redirectAttributes.addAttribute("info", "Please confirm your email before continue");
            mailService.sendMail(userDto);
            return "redirect:/signin";
        } else {
            redirectAttributes.addAttribute("error", "Account with this email already exists");
            return "redirect:/signup";
        }
    }
}
