package ru.itis.demo.services.intrfases;

public interface SenderService {
    void sendMessage(String subject, String email, String html);
}
