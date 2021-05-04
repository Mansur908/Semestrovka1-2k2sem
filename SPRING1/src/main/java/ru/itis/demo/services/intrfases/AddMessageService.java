package ru.itis.demo.services.intrfases;

import ru.itis.demo.dto.MessageForm;
import ru.itis.demo.security.details.UserDetailsImpl;

public interface AddMessageService {
    void addMessage(UserDetailsImpl user, MessageForm messageForm);
}
