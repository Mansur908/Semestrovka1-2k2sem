package ru.itis.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.demo.models.Support;

import java.util.List;
import java.util.Optional;

public interface SupportRepositiry extends JpaRepository<Support, Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM support WHERE to_user = :id OR user_id = :id")
    List<Support> findAllMessages(Long id);
}
