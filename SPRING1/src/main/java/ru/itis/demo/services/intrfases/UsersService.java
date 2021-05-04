package ru.itis.demo.services.intrfases;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.models.User;
import ru.itis.demo.security.details.UserDetailsImpl;

import java.io.IOException;
import java.util.List;

public interface UsersService {
    List<UserDto> getAllUsers();

    void banAll();

    User getUser(String email);

    String addImage(UserDetailsImpl user, MultipartFile file) throws IOException;
}
