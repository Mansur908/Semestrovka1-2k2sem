package ru.itis.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ru.itis.demo.mail.MailSendler;
import ru.itis.demo.dto.UserForm;
import ru.itis.demo.model.User;
import ru.itis.demo.repositories.UserRepository;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSendler mailSendler;

    @Override
    public String signIn(UserForm form) {
        User userCandidate = userRepository.findByEmail(form.getEmail());
        if (userCandidate != null && BCrypt.checkpw(form.getPassword(),userCandidate.getPassword())){
            mailSendler.send(form.getEmail(),"Вход на сайт","Выполнен вход на сайт");
            return "profile";
        }
        if (userCandidate != null && !(BCrypt.checkpw(form.getPassword(),userCandidate.getPassword()))){
            return "password";
        }
        return "nouser";
    }
}
