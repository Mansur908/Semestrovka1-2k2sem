package ru.itis.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(nullable = false, name = "created_at")
    private String createdAt;

    private boolean equalCookie;

//    @Override
//    public String toString() {
//        return "Message{" +
//                "id=" + id +
//                ", username=" + user.getUsername()+
//                ", text='" + text + '\'' +
//                ", createdAt=" + createdAt +
//                ", equalCookie=" + equalCookie +
//                '}';
//    }
}
