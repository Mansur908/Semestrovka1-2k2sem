package ru.itis.demo.dto;

import lombok.Data;

@Data
public class SearchForm {
    private String name;
    private int page;
    private int size;
}