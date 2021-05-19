package ru.itis.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "support")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Support {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(nullable = false, name = "created_at")
    private String createdAt;

    @Column(columnDefinition = "boolean default false")
    private boolean isAdmin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_user")
    private User to_user;

    @Override
    public String toString() {
        if (to_user != null) {
            return "{" +
                    "\"id\":\"" + id +
                    "\", \"user\":\"" + user.getName() +
                    "\", \"text\":\"" + text +
                    "\", \"createdAt\":\"" + createdAt +
                    "\", \"isAdmin\":\"" + isAdmin +
                    "\", \"to_user\":\"" + to_user.getName() +
                    "\"}";
        } else {
            return "{" +
                    "\"id\":\"" + id +
                    "\", \"user\":\"" + user.getName() +
                    "\", \"text\":\"" + text +
                    "\", \"createdAt\":\"" + createdAt +
                    "\", \"isAdmin\":\"" + isAdmin +
                    "\", \"to_user\":\"" + to_user +
                    "\"}";
        }
    }
}