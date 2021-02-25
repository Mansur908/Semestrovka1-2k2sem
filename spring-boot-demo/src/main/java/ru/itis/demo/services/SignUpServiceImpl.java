package ru.itis.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import ru.itis.demo.mail.MailSendler;
import ru.itis.demo.dto.UserForm;


import ru.itis.demo.model.User;
import ru.itis.demo.repositories.UserRepository;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSendler mailSendler;

    @Override
    public boolean signUp(UserForm form) {
        User newUser = User.builder()
                .email(form.getEmail())
                .password(BCrypt.hashpw(form.getPassword(), BCrypt.gensalt(10)))
                .build();

        if (userRepository.findByEmail(form.getEmail()) != null){
            return false;
        }
        else {
            userRepository.save(newUser);
            mailSendler.send(form.getEmail(), "Регистрация на сайте", "Вы зарегистрировались на сайте");
            return true;
        }
    }
}