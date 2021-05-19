package ru.itis.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

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

    public String toJson() {
        return "{" +
                "\"id\":\"" + id +
                "\", \"user\":\"" + user.getName() +
                "\", \"text\":\"" + text +
                "\", \"createdAt\":\"" + createdAt +
                "\", \"equalCookie\":\"" + equalCookie +
                "\"}";
    }
}
