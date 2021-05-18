package ru.itis.demo.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.demo.models.Message;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.MessageRepository;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.security.details.UserDetailsImpl;
import ru.itis.demo.services.intrfases.MessageService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;


    @Override
    public void addMessage(User user, String text) {
        Message message = new Message();
        message.setUser(user);
        message.setText(text);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        message.setCreatedAt(LocalDateTime.now().format(formatter));
        messageRepository.save(message);
    }

    @Override
    public String getUserMessage(UserDetailsImpl user) {
        User user1 = userRepository.findByEmail(user.getUsername()).get();
        List<Message> mes = messageRepository.findAll();
        List<String> messages = new ArrayList<>();
        for (Message m : mes) {
            if (m.getUser().getId().equals(user1.getId())) {
                m.setEqualCookie(true);
                messages.add(m.toJson());
            } else {
                messages.add(m.toJson());
            }
        }
        return messages.toString();
    }

}