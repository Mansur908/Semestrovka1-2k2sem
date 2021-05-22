package ru.itis.demo.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.demo.dto.UserForm;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.services.intrfases.SignUpService;

import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String signUp(UserForm form) {
        if (userRepository.existsByEmail(form.getEmail()))
            return "Пользователь существует";
        User newUser = User.builder()
                .name(form.getName())
                .email(form.getEmail())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .role(User.Role.USER)
                .state(User.State.ACTIVE)
                .currentConfirmationCode(UUID.randomUUID().toString())
                .build();

        userRepository.save(newUser);
        return "Вы зарегистрировались";
    }
}
