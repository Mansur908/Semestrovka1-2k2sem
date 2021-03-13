package ru.itis.demo.services.interfaces;


import ru.itis.demo.dto.UserDto;

public interface MailService {
    void sendMail(UserDto userDto);
}
