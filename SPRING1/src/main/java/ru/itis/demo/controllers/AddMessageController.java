package ru.itis.demo.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.MessageForm;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.security.details.UserDetailsImpl;
import ru.itis.demo.services.intrfases.MessageService;


@Controller
@RequestMapping("/addMess")
@RequiredArgsConstructor
public class AddMessageController {
    private final UserRepository userRepository;
    private final MessageService messageService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void  getUsersBySearchForm(@AuthenticationPrincipal UserDetailsImpl user,@RequestBody MessageForm messageForm) {
        System.out.println(messageForm.getText());
        User user1 = userRepository.findByEmail(user.getUsername()).get();
        messageService.addMessage(user1,messageForm.getText());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleException() {
        return "Exception";
    }
}