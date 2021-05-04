package ru.itis.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestLoginDto {
    String email;
    String password;
}
