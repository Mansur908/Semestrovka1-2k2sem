package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.demo.dto.SmsInfo;
import ru.itis.demo.services.intrfases.SmsSender;

import javax.annotation.security.PermitAll;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SmsSenderController {
    private final SmsSender smsSender;

    @PermitAll
    @GetMapping("/sendSms")
    public String sendSmsMessage() {
        return smsSender.sendSms("+79869049899", "Привет");
    }

    @PermitAll
    @GetMapping("/checkStatus")
    public String checkSmsStatus(@ModelAttribute SmsInfo smsInfo){
        return smsSender.checkSmsStatus(smsInfo);
    }
}