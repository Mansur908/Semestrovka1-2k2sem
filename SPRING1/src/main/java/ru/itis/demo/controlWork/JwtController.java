package ru.itis.demo.controlWork;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.demo.dto.RestLoginDto;
import ru.itis.demo.dto.TokenDto;
import ru.itis.demo.services.intrfases.RestLoginService;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
public class JwtController {
    private final RestLoginService restLoginService;

    // Метод для получение JWT токена,конфигурация находится в папке jwt
    @GetMapping("/restsignin")
    public ResponseEntity<TokenDto> getRestSignInPage(@RequestBody RestLoginDto loginDto) throws AccessDeniedException {
        return ResponseEntity.ok(restLoginService.signIn(loginDto));
    }
}
