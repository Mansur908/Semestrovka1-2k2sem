package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.demo.dto.MessageForm;
import ru.itis.demo.models.Message;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.MessageRepository;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.security.details.UserDetailsImpl;
import ru.itis.demo.services.intrfases.MessageService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;


    @GetMapping
    public String getMessagePage(@AuthenticationPrincipal UserDetailsImpl user,
                                 HttpServletResponse response,
                                 Model model) throws IOException {
        User user1 = userRepository.findByEmail(user.getUsername()).get();
        List<Message> mes = messageRepository.findAll();
        List<Message> messages = new ArrayList<>();
        for (Message m : mes){
            if (m.getUser().getId().equals(user1.getId())){
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

//    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public String addMessage(@AuthenticationPrincipal UserDetailsImpl user,
                             @Valid MessageForm messageForm){
        System.out.println(messageForm.getText());
        System.out.println("-------------------------");
        User user1 = userRepository.findByEmail(user.getUsername()).get();
        messageService.addMessage(user1,messageForm.getText());

        return "redirect:/message";
    }
}