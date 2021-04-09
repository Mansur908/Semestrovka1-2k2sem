package ru.itis.demo.services.intrfases;


import ru.itis.demo.dto.UserDto;

public interface MailService {
    void sendMail(UserDto userDto);
}
