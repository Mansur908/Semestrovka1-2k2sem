package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.dto.UserForm;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.services.intrfases.MailService;
import ru.itis.demo.services.intrfases.SignUpService;
import ru.itis.demo.services.intrfases.UsersService;


import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;
    private final UsersService usersService;
    private final MailService mailService;


    @PermitAll
    @GetMapping("/signup")
    public String getSignUpPage(Model model) {
        model.addAttribute("userForm",new UserForm());
        return "sign_up_page";
    }

    @PermitAll
    @PostMapping("/signup")
    public String signUp(@Valid UserForm form, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            String str = signUpService.signUp(form);
            if (str.equals("Вы зарегистрировались")) {
                UserDto userDto = new UserDto();
                userDto.setId(usersService.getUser(form.getEmail()).getId());
                userDto.setUsername(form.getName());
                userDto.setEmail(form.getEmail());
                userDto.setCode(usersService.getUser(form.getEmail()).getCurrentConfirmationCode());
                mailService.sendMail(userDto);
                return "redirect:/signin";
            } else {
                model.addAttribute("message", str);
                return "sign_up_page";
            }
        }
        else {
            model.addAttribute("userForm",form);
            return "sign_up_page";
        }
    }
}
