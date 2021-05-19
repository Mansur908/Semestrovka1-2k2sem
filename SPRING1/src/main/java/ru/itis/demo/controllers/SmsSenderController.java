package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.SmsSenderForm;
import ru.itis.demo.services.intrfases.SmsSender;

@Controller
@RequiredArgsConstructor
public class SmsSenderController {
    private final SmsSender smsSender;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/sms")
    public String getSmsMessage() {
        return "sms_sender";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    @PostMapping("/sms")
    public String sendSmsMessage(SmsSenderForm form) {
        return smsSender.sendSms(form.getNumber(), form.getText());
    }
}