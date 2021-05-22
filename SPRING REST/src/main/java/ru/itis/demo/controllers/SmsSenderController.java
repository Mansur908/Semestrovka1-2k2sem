package ru.itis.demo.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.demo.dto.SmsSenderForm;
import ru.itis.demo.services.intrfases.SmsSender;


@RestController
@RequiredArgsConstructor
public class SmsSenderController {
    private final SmsSender smsSender;

    @ApiOperation(value = "Отправить SMS")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/sendSms")
    public ResponseEntity<String> sendSmsMessage(@RequestBody SmsSenderForm form) {
        return ResponseEntity.ok(smsSender.sendSms(form.getNumber(), form.getText()));
    }
}
