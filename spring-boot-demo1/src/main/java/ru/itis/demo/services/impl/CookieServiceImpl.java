package ru.itis.demo.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.demo.model.MyCookie;
import ru.itis.demo.model.User;
import ru.itis.demo.repositories.CookieRepository;
import ru.itis.demo.services.interfaces.CookieService;

import javax.servlet.http.Cookie;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CookieServiceImpl implements CookieService {
    private final CookieRepository cookieRepository;

    @Override
    public boolean checkCookie(String cookieValue) {
        return cookieValue != null;
    }

    @Override
    public Cookie createCookie(User user) {
        String value = UUID.randomUUID().toString();
        MyCookie cookie = MyCookie
                .fromValueAndUser(value, user);
        cookieRepository.save(cookie);
        return new Cookie("AuthCookie", value);
    }
}