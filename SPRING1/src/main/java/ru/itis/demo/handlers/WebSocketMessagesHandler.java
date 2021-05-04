package ru.itis.demo.handlers;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.demo.dto.MessageForm;
import ru.itis.demo.dto.SupportForm;
import ru.itis.demo.models.Message;
import ru.itis.demo.models.Support;
import ru.itis.demo.repositories.MessageRepository;
import ru.itis.demo.repositories.SupportRepositiry;
import ru.itis.demo.repositories.UserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class WebSocketMessagesHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final SupportRepositiry supportRepositiry;
    private final UserRepository userRepository;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        SupportForm message1 = objectMapper.readValue(message.getPayload(), SupportForm.class);
        if (message1.getText().equals("/connected") && message1.getTo_user() != null){
            String mes = supportRepositiry.findAllMessages(message1.getTo_user()).toString();
            TextMessage m = new TextMessage(mes);
            session.sendMessage(m);
        }
        if (message1.getText().equals("/connected") && message1.getTo_user() == null) {
            String mes = supportRepositiry.findAllMessages(message1.getUser_id()).toString();
            TextMessage m = new TextMessage(mes);
            session.sendMessage(m);
        }
        if (!message1.getText().equals("/connected") && message1.getTo_user() == null) {
            Support support = new Support();
            support.setText(message1.getText());
            support.setUser(userRepository.findById(message1.getUser_id()).get());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
            support.setCreatedAt(LocalDateTime.now().format(formatter));
            supportRepositiry.save(support);
            String mes = supportRepositiry.findAllMessages(message1.getUser_id()).toString();
            TextMessage m = new TextMessage(mes);
            session.sendMessage(m);
        }
        if (!message1.getText().equals("/connected") && message1.getTo_user() != null){
            if (!userRepository.existsById(message1.getTo_user())){
                String mes = "[{\"id\":\"0\", \"user\":\"A\", \"text\":\"Пользователь не существует\", \"createdAt\":\"1\", \"isAdmin\":\"true\", \"to_user\":\"null\"}]";
                TextMessage m = new TextMessage(mes);
                session.sendMessage(m);
            }
            else {
                Support support = new Support();
                support.setText(message1.getText());
                support.setUser(userRepository.findById(message1.getUser_id()).get());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
                support.setCreatedAt(LocalDateTime.now().format(formatter));
                support.setTo_user(userRepository.findById(message1.getTo_user()).get());
                support.setAdmin(true);
                supportRepositiry.save(support);
                String mes = supportRepositiry.findAllMessages(message1.getTo_user()).toString();
                TextMessage m = new TextMessage(mes);
                session.sendMessage(m);
            }
        }
    }
}