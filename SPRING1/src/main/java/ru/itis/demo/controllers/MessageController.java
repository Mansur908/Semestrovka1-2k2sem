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
import ru.itis.demo.services.intrfases.MailService;
import ru.itis.demo.services.intrfases.MessageService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping
    public String getMessagePage()  {
        return "message_page";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addMessage(@AuthenticationPrincipal UserDetailsImpl user){
        return messageService.addUserMessage(user);
    }
}