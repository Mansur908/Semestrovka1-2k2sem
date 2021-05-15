package ru.itis.demo.services.intrfases;

import ru.itis.demo.dto.RestLoginDto;
import ru.itis.demo.dto.TokenDto;

import java.nio.file.AccessDeniedException;

public interface RestLoginService {
    TokenDto signIn(RestLoginDto loginDto) throws AccessDeniedException;
}
