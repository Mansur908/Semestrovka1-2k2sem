package ru.itis.demo.services;

import ru.itis.demo.dto.UserForm;

public interface SignInService {

    String signIn(UserForm form);
}