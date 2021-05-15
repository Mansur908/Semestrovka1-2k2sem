package ru.itis.demo.services.intrfases;


import ru.itis.demo.models.User;
import ru.itis.demo.security.details.UserDetailsImpl;

public interface MessageService {
    void addMessage(User user, String text);

    String getUserMessage(UserDetailsImpl user);
}
