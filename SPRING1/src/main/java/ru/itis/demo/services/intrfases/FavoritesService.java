package ru.itis.demo.services.intrfases;

import ru.itis.demo.models.Product;

import java.util.List;

public interface FavoritesService {
    void addFavorites(Long userId,Long productId);

    List<Product> getProductsByUserId(Long userId);

    void deleteFavorites(Long userId,Long productId);

}
