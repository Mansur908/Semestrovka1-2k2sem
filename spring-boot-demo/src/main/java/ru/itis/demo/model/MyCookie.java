package ru.itis.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cookie")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MyCookie {
    @Id
    private String uuid;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public static MyCookie fromValueAndUser(String value, User user) {
        return MyCookie.builder()
                .uuid(value)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();
    }
}