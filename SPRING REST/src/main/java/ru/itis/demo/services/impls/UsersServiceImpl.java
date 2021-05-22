package ru.itis.demo.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.security.details.UserDetailsImpl;
import ru.itis.demo.services.intrfases.UsersService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static ru.itis.demo.dto.UserDto.from;

@Component
@RequiredArgsConstructor
class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;

    @Value("${upload.path}")
    private String uploadPath;

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

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public String addImage(UserDetailsImpl user, MultipartFile file) throws IOException {
        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            userRepository.addImageNameByUserId(user.getId(), resultFileName);
            return resultFileName;
        }
        return null;
    }
}