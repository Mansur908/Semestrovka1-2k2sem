package ru.itis.demo.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.demo.models.Message;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.MessageRepository;
import ru.itis.demo.services.intrfases.MessageService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public void addMessage(User user, String text) {
        Message message = new Message();
        message.setUser(user);
        message.setText(text);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        message.setCreatedAt(LocalDateTime.now().format(formatter));
        messageRepository.save(message);
    }
}