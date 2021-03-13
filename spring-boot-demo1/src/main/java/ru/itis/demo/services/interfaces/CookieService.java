package ru.itis.demo.services.interfaces;

import ru.itis.demo.model.User;

import javax.servlet.http.Cookie;


public interface CookieService {
    boolean checkCookie(String cookieValue);

    Cookie createCookie(User userDto);
}
