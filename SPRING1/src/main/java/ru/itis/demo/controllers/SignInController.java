package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.demo.dto.RestLoginDto;
import ru.itis.demo.dto.TokenDto;
import ru.itis.demo.repositories.SupportRepositiry;
import ru.itis.demo.services.intrfases.RestLoginService;

import javax.annotation.security.PermitAll;
import java.nio.file.AccessDeniedException;

@Controller
public class SignInController {

    @Autowired
    private RestLoginService restLoginService;

    @PermitAll
    @GetMapping("/signin")
    public String getSignInPage() {
        return "sign_in_page";
    }

    @GetMapping("/restsignin")
    @ResponseBody
    public ResponseEntity<TokenDto> getRestSignInPage(@RequestBody RestLoginDto loginDto) throws AccessDeniedException {
        return ResponseEntity.ok(restLoginService.signIn(loginDto));
    }
}
