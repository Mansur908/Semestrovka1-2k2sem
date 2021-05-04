package ru.itis.demo.dto;

import lombok.Data;

import javax.validation.constraints.Email;


@Data
public class UserForm {
    @Email(message = "{errors.incorrect.email}")
    private String email;
    private String name;
    private String password;
}
