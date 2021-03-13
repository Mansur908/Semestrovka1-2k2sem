package ru.itis.demo.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.demo.dto.UserAuthForm;
import ru.itis.demo.exceptions.LoginProcessErrorException;
import ru.itis.demo.model.User;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.services.interfaces.SignInService;


@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {
    private final UserRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signIn(UserAuthForm signInForm) throws LoginProcessErrorException {
        User user = usersRepository.findByEmail(signInForm.getEmail())
                .orElseThrow(() -> new LoginProcessErrorException("User not found"));
        boolean passwordResult = passwordEncoder.matches(signInForm.getPassword(), user.getPassword());
        if(passwordResult) return user;
        return null;
    }
}
