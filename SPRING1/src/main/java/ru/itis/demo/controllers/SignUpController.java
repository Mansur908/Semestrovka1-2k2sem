package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.dto.UserForm;
import ru.itis.demo.dto.UserMapper;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.services.intrfases.MailService;
import ru.itis.demo.services.intrfases.SignUpService;


import javax.annotation.security.PermitAll;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;
    private final UserRepository userRepository;
    private final MailService mailService;


    @PermitAll
    @GetMapping("/signup")
    public String getSignUpPage() {
        return "sign_up_page";
    }

    @PermitAll
    @PostMapping("/signup")
    public String signUp(UserForm form, Model model) {
        String str = signUpService.signUp(form);

//        User user = userRepository.findByEmail(form.getEmail()).get();
//        mailService.sendMail(UserMapper.INSTANCE.toDto(user));

        UserDto userDto = new UserDto();
        userDto.setId(userRepository.findByEmail(form.getEmail()).get().getId());
        userDto.setUsername(form.getName());
        userDto.setEmail(form.getEmail());
        userDto.setCode(userRepository.findByEmail(form.getEmail()).get().getCurrentConfirmationCode());
        mailService.sendMail(userDto);

        if (str.equals("Вы зарегистрировались"))
        return "redirect:/signin";
        else {
            model.addAttribute("message",str);
            return "sign_up_page";
        }
    }
}
