package ru.itis.demo.services.intrfases;



import ru.itis.demo.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> getAllUsers();

    void banAll();
}
