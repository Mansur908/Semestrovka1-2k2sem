package ru.itis.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.demo.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    Optional<User> findByCurrentConfirmationCode(String code);

    @Query(nativeQuery = true,value = "UPDATE account SET image_name = :imageName where id = :id returning 1")
    void addImageNameByUserId(Long id,String imageName);

}
