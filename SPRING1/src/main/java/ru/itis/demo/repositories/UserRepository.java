package ru.itis.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.demo.models.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    @Query(nativeQuery = true, value = "UPDATE account SET image = :imageName where id = :id returning 1")
    void addImageNameByUserId(Long id, String imageName);

    @Query("select u from User u where lower(u.name) like lower(concat('%', :nameToFind,'%')) ")
    Page<User> findAllByUsernameIgnoreCase(@Param("nameToFind") String username,
                                           Pageable pageable);

    @Query(nativeQuery = true, value = "INSERT INTO favorites (user_id,product_id) VALUES (:userId,:productId) returning 1")
    void addFavorites(Long userId, Long productId);

    @Query(nativeQuery = true, value = "SELECT 1 FROM favorites WHERE user_id=:userId AND product_id=:productId")
    String findFavorites (Long userId, Long productId);

    @Query(nativeQuery = true, value = "SELECT product_id FROM favorites WHERE user_id=:userId")
    List<Long> findProductsByUserId (Long userId);

    @Query(nativeQuery = true, value = "DELETE FROM favorites WHERE user_id=:userId AND product_id=:productId returning 1")
    List<Long> deleteFavoritesByUserId (Long userId, Long productId);


    Optional<User> findByCurrentConfirmationCode(String code);

    Optional<User> findById(Long id);
}
