package ru.itis.demo.dto;

import lombok.Data;


@Data
public class UserForm {
    private String name;
    private String email;
    private String password;
}
