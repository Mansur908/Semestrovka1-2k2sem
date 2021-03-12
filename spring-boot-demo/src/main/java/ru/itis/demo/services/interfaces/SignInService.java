package ru.itis.demo.services.interfaces;

import ru.itis.demo.dto.SignUpForm;
import ru.itis.demo.dto.UserAuthForm;
import ru.itis.demo.exceptions.LoginProcessErrorException;
import ru.itis.demo.model.User;

public interface SignInService {

    User signIn(UserAuthForm form) throws LoginProcessErrorException;
}