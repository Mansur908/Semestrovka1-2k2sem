package ru.itis.demo.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.dto.UserForm;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.services.intrfases.MailService;
import ru.itis.demo.services.intrfases.SignUpService;
import ru.itis.demo.services.intrfases.UsersService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsersService usersService;
    private final MailService mailService;

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

    public String sendMail(UserForm form){
        UserDto userDto = new UserDto();
        userDto.setId(usersService.getUser(form.getEmail()).getId());
        userDto.setUsername(form.getName());
        userDto.setEmail(form.getEmail());
        userDto.setCode(usersService.getUser(form.getEmail()).getCurrentConfirmationCode());
        mailService.sendMail(userDto);
        return "redirect:/signin";
    }
}
