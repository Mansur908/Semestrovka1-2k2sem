package ru.itis.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.demo.models.Product;
import ru.itis.demo.models.User;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByType(String type);

    @Query("select p from Product p where lower(p.type) like lower(concat('%', :nameToFind,'%')) ")
    Page<Product> findAll(@Param("nameToFind") String username,
                                           Pageable pageable);
}
