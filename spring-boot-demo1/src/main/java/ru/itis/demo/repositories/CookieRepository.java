package ru.itis.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.demo.model.MyCookie;
import ru.itis.demo.model.User;

import java.util.Optional;

public interface CookieRepository extends JpaRepository<MyCookie, String> {

    Optional<MyCookie> findByUuid (String uuid);
}
