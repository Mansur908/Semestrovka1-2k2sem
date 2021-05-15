package ru.itis.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String hashPassword;

    private String image;

    private String currentConfirmationCode;

    @Column(columnDefinition = "boolean default false")
    private boolean proved;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum State {
        ACTIVE, BANNED
    }

    public enum Role {
        USER, ADMIN
    }

    public boolean isActive() {
        return this.state == State.ACTIVE;
    }

    public boolean isBanned() {
        return this.state == State.BANNED;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    public boolean isProved() {
        return this.proved;
    }

    public String toJson() {
        return "{" +
                "\"id\":\"" + id +
                "\", \"name\":\"" + name +
                "\", \"email\":\"" + email +
                "\", \"hashPassword\":\"" + hashPassword +
                "\", \"image\":\"" + image +
                "\", \"state\":\"" + state +
                "\", \"role\":\"" + role +
                "\"}";
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id +
                "\", \"name\":\"" + name +
                "\", \"email\":\"" + email +
                "\", \"hashPassword\":\"" + hashPassword +
                "\", \"image\":\"" + image +
                "\", \"state\":\"" + state +
                "\", \"role\":\"" + role +
                "\"}";
    }
}
