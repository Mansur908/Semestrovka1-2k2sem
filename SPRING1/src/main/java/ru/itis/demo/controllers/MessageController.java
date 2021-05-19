package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.security.details.UserDetailsImpl;
import ru.itis.demo.services.intrfases.MessageService;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getMessagePage() {
        return "message_page";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addMessage(@AuthenticationPrincipal UserDetailsImpl user) {
        return messageService.getUserMessage(user);
    }
}