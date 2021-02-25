package ru.itis.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}