package ru.itis.demo.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.demo.dto.MessageForm;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.security.details.UserDetailsImpl;
import ru.itis.demo.services.intrfases.AddMessageService;
import ru.itis.demo.services.intrfases.MessageService;

@Service
@RequiredArgsConstructor
public class AddMessageServiceImpl implements AddMessageService {
    private final UserRepository userRepository;
    private final MessageService messageService;

    @Override
    public void addMessage(String email, MessageForm messageForm) {
        messageService.addMessage(email,messageForm.getText());
    }
}
