package ru.itis.demo.dto;

import ru.itis.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User:  SimonOnBoard
 * Project:  spring-basic-course
 * Package:  com.itis.kpfu.education.simononboard.spring.basics.dto
 * Date:  26.02.2021
 * Time:  10:33
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private Long id;
    private String username;
    private String code;
    private String email;
    public static UserDto fromUser(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .id(user.getId())
                .code(user.getCurrentConfirmationCode())
                .email(user.getEmail())
                .build();
    }
}
