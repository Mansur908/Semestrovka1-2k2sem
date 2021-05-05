package ru.itis.demo.controlWork;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String getRole(Long id){
        return userRepository.findById(id).get().getRole().toString();
    }
}
