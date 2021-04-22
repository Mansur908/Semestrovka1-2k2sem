package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.models.Message;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.MessageRepository;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.security.details.UserDetailsImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping
    public String getMessagePage()  {
        return "message_page";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addMessage(@AuthenticationPrincipal UserDetailsImpl user){
        User user1 = userRepository.findByEmail(user.getUsername()).get();
        List<Message> mes = messageRepository.findAll();
        List<String> messages = new ArrayList<>();
        for (Message m : mes){
            if (m.getUser().getId().equals(user1.getId())){
                m.setEqualCookie(true);
                messages.add(m.toJson());
            }
            else {
                messages.add(m.toJson());
            }
        }
        return messages.toString();
    }
}