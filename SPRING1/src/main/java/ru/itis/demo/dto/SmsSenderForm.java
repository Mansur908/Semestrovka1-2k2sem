package ru.itis.demo.dto;

import lombok.Data;

@Data
public class SmsSenderForm {
    private String number;
    private String text;
}
