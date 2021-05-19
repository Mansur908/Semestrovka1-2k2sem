package ru.itis.demo.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.demo.dto.SupportForm;
import ru.itis.demo.models.Support;
import ru.itis.demo.repositories.SupportRepositiry;
import ru.itis.demo.repositories.UserRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class WebSocketMessagesHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final SupportRepositiry supportRepositiry;
    private final UserRepository userRepository;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        SupportForm message1 = objectMapper.readValue(message.getPayload(), SupportForm.class);
        if (message1.getText().equals("/connected") && message1.getToUser() != null) {
            String mes = supportRepositiry.findAllMessages(message1.getToUser()).toString();
            TextMessage m = new TextMessage(mes);
            session.sendMessage(m);
        }
        if (message1.getText().equals("/connected") && message1.getToUser() == null) {
            String mes = supportRepositiry.findAllMessages(message1.getUserId()).toString();
            TextMessage m = new TextMessage(mes);
            session.sendMessage(m);
        }
        if (!message1.getText().equals("/connected") && message1.getToUser() == null) {
            Support support = new Support();
            support.setText(message1.getText());
            support.setUser(userRepository.findById(message1.getUserId()).get());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
            support.setCreatedAt(LocalDateTime.now().format(formatter));
            supportRepositiry.save(support);
            String mes = supportRepositiry.findAllMessages(message1.getUserId()).toString();
            TextMessage m = new TextMessage(mes);
            session.sendMessage(m);
        }
        if (!message1.getText().equals("/connected") && message1.getToUser() != null) {
            if (!userRepository.existsById(message1.getToUser())) {
                String mes = "[{\"id\":\"0\", \"user\":\"A\", \"text\":\"Пользователь не существует\", \"createdAt\":\"1\", \"isAdmin\":\"true\", \"to_user\":\"null\"}]";
                TextMessage m = new TextMessage(mes);
                session.sendMessage(m);
            } else {
                Support support = new Support();
                support.setText(message1.getText());
                support.setUser(userRepository.findById(message1.getUserId()).get());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
                support.setCreatedAt(LocalDateTime.now().format(formatter));
                support.setTo_user(userRepository.findById(message1.getToUser()).get());
                support.setAdmin(true);
                supportRepositiry.save(support);
                String mes = supportRepositiry.findAllMessages(message1.getToUser()).toString();
                TextMessage m = new TextMessage(mes);
                session.sendMessage(m);
            }
        }
    }
}