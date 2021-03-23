package ru.itis.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@PropertySource(value = "classpath:/application.properties")
@RequiredArgsConstructor
public class ApplicationContextConfig implements WebMvcConfigurer {
    private final Environment environment;

    @Value("${upload.path1}")
    private String uploadPath;


//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Bean
//    public ExecutorService executorService() {
//        int i = Runtime.getRuntime().availableProcessors();
//        return Executors.newFixedThreadPool(i);
//    }
//
//    @Bean
//    public Map<String, String> templateParameters() {
//        Map<String, String> model = new HashMap<>();
//        model.put("location", environment.getProperty("location"));
//        model.put("signature", environment.getProperty("link"));
//        return model;
//    }
//
//    @Bean
//    @Primary
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.mail.ru");
//        mailSender.setPort(587);
//
//        mailSender.setUsername("top2488@mail.ru");
//        mailSender.setPassword("mansur1213");
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.ssl.trust", "smtp.mail.ru");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//        return mailSender;
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file://" + uploadPath + "/");


    }

}
