package ru.itis.demo.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.demo.dto.SignUpForm;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.model.User;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.services.interfaces.SignUpService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    private final BCryptPasswordEncoder encoder;
    private final UserRepository usersRepository;

    @Override
    public UserDto signUp(SignUpForm form) {
        if (usersRepository.existsByEmail(form.getEmail())) return null;
        User user = User.fromSignUpForm(form);
        user.setCurrentConfirmationCode(UUID.randomUUID().toString());
        user.setPassword(encoder.encode(form.getPassword()));
        usersRepository.save(user);
        return UserDto.fromUser(user);
    }
}