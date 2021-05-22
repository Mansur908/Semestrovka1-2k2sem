package ru.itis.demo.services.intrfases;


import ru.itis.demo.dto.SmsInfo;

public interface SmsSender {
    String sendSms(String phone,String text);

    String checkSmsStatus(SmsInfo smsInfo);
}