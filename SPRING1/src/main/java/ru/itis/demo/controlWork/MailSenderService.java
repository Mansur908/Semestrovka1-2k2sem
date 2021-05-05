package ru.itis.demo.controlWork;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import ru.itis.demo.services.intrfases.SenderService;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final JavaMailSender mailSender;
    private final ExecutorService executorService;

    @Value("${spring.mail.username}")
    private String senderName;

    public void sendMessage(String subject, String mail, String text) {
        Runnable runnable = () -> {
            MimeMessagePreparator message = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                        StandardCharsets.UTF_8.name());
                messageHelper.setFrom(senderName);
                messageHelper.setTo(mail);
                messageHelper.setSubject(subject);
                messageHelper.setText(text);
            };
            mailSender.send(message);
        };
        executorService.submit(runnable);
    }
}

