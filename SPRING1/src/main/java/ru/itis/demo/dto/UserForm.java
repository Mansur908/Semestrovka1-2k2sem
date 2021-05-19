package ru.itis.demo.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserForm {
    @Email(message = "{errors.incorrect.email}")
    private String email;
    @Size(min=3, max=10, message = "{errors.incorrect.name}")
    private String name;
    @Size(min=7, max=15, message = "{errors.incorrect.password}")
    private String password;
}