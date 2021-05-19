package ru.itis.demo.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.MessageForm;
import ru.itis.demo.security.details.UserDetailsImpl;
import ru.itis.demo.services.intrfases.AddMessageService;

@Controller
@RequestMapping("/message/add")
@RequiredArgsConstructor
public class AddMessageController {
    private final AddMessageService messageService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void getUsersBySearchForm(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody MessageForm messageForm) {
        messageService.addMessage(user, messageForm);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleException() {
        return "Exception";
    }
}