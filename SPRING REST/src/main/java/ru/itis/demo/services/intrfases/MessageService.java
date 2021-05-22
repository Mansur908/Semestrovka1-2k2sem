package ru.itis.demo.services.intrfases;


import ru.itis.demo.models.Message;
import ru.itis.demo.details.UserDetailsImpl;

import java.util.List;

public interface MessageService {
    List<Message> addMessage(String email, String text);

    List<Message> getUserMessage();
}
