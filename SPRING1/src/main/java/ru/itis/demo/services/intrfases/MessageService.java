package ru.itis.demo.services.intrfases;


import ru.itis.demo.models.User;

public interface MessageService {
    void addMessage(User user, String text);
}
