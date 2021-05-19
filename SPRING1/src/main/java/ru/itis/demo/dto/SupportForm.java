package ru.itis.demo.dto;

import lombok.Data;

@Data
public class SupportForm {
    private String text;
    private Long userId;
    private Long toUser;
}