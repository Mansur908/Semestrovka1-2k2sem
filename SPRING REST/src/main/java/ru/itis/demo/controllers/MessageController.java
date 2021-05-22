package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.MessageForm;
import ru.itis.demo.models.Message;
import ru.itis.demo.security.details.UserDetailsImpl;
import ru.itis.demo.services.intrfases.MessageService;

import java.util.List;

@RestController
@RequestMapping("/user/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping
    public ResponseEntity<List<Message>> getMessagePage(){
        return ResponseEntity.ok(messageService.getUserMessage());
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<List<Message>> addMessage(@RequestBody MessageForm form){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        return ResponseEntity.ok(messageService.addMessage(userDetails.getUsername(),form.getText()));
    }
}