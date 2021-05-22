package ru.itis.demo.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.demo.dto.RestLoginDto;
import ru.itis.demo.dto.TokenDto;
import ru.itis.demo.services.intrfases.RestLoginService;

import java.nio.file.AccessDeniedException;

@RestController
public class SignInController {

    @Autowired
    private RestLoginService restLoginService;

    @ApiOperation(value = "Получить Jwt токен")
    @PostMapping("/signin")
    public ResponseEntity<TokenDto> getRestSignInPage(@RequestBody RestLoginDto loginDto) throws AccessDeniedException {
        if (!restLoginService.checkUser(loginDto)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(restLoginService.signIn(loginDto));
    }
}
