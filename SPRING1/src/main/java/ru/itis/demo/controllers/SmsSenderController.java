package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.SmsSenderForm;
import ru.itis.demo.services.intrfases.SmsSender;


@Controller
@RequiredArgsConstructor
@Slf4j
public class SmsSenderController {
    private final SmsSender smsSender;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/sendSms")
    public String getSmsMessage() {
        return "sms_sender";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    @PostMapping("/sendSms")
    public String sendSmsMessage(SmsSenderForm form) {
        return smsSender.sendSms(form.getNumber(), form.getText());
    }


//    @PermitAll
//    @GetMapping("/checkStatus")
//    @ResponseBody
//    public String checkSmsStatus(@ModelAttribute SmsInfo smsInfo){
//        return smsSender.checkSmsStatus(smsInfo);
//    }
}