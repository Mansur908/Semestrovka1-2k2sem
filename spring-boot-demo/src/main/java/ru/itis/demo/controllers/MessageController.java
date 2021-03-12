package ru.itis.demo.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.demo.dto.MessageForm;
import ru.itis.demo.model.Message;
import ru.itis.demo.model.User;
import ru.itis.demo.repositories.CookieRepository;
import ru.itis.demo.repositories.MessageRepository;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.services.interfaces.CookieService;
import ru.itis.demo.services.interfaces.MessageService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final CookieService cookieService;
    private final MessageService messageService;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final CookieRepository cookieRepository;

    @GetMapping
    public String getMessagePage(@CookieValue(value = "AuthCookie", required = false) String cookieValue,
                                 HttpServletResponse response,
                                 Model model) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Message> mes = messageRepository.findAll();
        List<Message> messages = new ArrayList<>();
        for (Message m : mes){
            if (m.getUser().getId().equals(cookieRepository.findByUuid(cookieValue).get().getUser().getId())){
                m.setEqualCookie(true);
                messages.add(m);
            }
            else {
                messages.add(m);
            }
        }
        model.addAttribute("mess",messages);
        return "message_page";
    }

    @PostMapping
    public String addMessage(@CookieValue(value = "AuthCookie", required = false) String cookieValue,
                             @Valid MessageForm messageForm){
        User user = cookieRepository.findByUuid(cookieValue).get().getUser();
        messageService.addMessage(user,messageForm.getText());

        return "redirect:/message";
    }
}