package ru.itis.demo.services.interfaces;

import ru.itis.demo.model.User;

public interface MessageService {
    void addMessage(User user,String text);
}
