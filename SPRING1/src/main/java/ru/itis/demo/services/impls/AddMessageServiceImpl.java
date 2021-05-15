package ru.itis.demo.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.demo.dto.MessageForm;
import ru.itis.demo.models.User;
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
    public void addMessage(UserDetailsImpl user, MessageForm messageForm) {
        User user1 = userRepository.findByEmail(user.getUsername()).get();
        messageService.addMessage(user1, messageForm.getText());
    }
}
