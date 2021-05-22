package ru.itis.demo.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.dto.UserForm;
import ru.itis.demo.services.intrfases.MailService;
import ru.itis.demo.services.intrfases.SignUpService;
import ru.itis.demo.services.intrfases.UsersService;

@RestController
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;
    private final UsersService usersService;
    private final MailService mailService;

    @ApiOperation(value = "Регистрация")
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserForm form) {
        String str = signUpService.signUp(form);
        if (str.equals("Вы зарегистрировались")) {
            UserDto userDto = new UserDto();
            userDto.setId(usersService.getUser(form.getEmail()).getId());
            userDto.setUsername(form.getName());
            userDto.setEmail(form.getEmail());
            userDto.setCode(usersService.getUser(form.getEmail()).getCurrentConfirmationCode());
            mailService.sendMail(userDto);
        }
        return ResponseEntity.ok("{\"message\":\""+str+"\"}");
    }
}
