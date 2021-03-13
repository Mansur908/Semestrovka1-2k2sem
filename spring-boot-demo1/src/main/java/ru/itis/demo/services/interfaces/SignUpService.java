package ru.itis.demo.services.interfaces;

import ru.itis.demo.dto.SignUpForm;
import ru.itis.demo.dto.UserDto;

public interface SignUpService {

    UserDto signUp(SignUpForm form);
}
