package ru.itis.demo.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.services.intrfases.ConfirmationService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmationServiceImpl implements ConfirmationService {
    private final UserRepository usersRepository;

    @Override
    public boolean confirmByCode(String code) {
        Optional<User> userCandidate = usersRepository.findByCurrentConfirmationCode(code);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            user.setProved(true);
            usersRepository.save(user);
        }
        return userCandidate.isPresent();
    }
}