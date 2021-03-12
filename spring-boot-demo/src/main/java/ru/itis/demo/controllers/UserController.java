package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pro")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public String getMessagePage(){
        return "file";
    }

}
