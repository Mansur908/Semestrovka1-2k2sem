package ru.itis.demo.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.services.intrfases.UsersService;

import java.util.List;

import static ru.itis.demo.dto.UserDto.from;

@Component
class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return from(userRepository.findAll());
    }

    @Override
    public void banAll() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (!user.isAdmin()) {
                user.setState(User.State.BANNED);
                userRepository.save(user);
            }
        }
    }
}