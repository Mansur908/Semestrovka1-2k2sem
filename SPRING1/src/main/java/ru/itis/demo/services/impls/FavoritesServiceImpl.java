package ru.itis.demo.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.demo.models.Product;
import ru.itis.demo.repositories.ProductRepository;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.services.intrfases.FavoritesService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritesServiceImpl implements FavoritesService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public void addFavorites(Long userId, Long productId) {
        if (userRepository.findFavorites(userId,productId) == null) {
            userRepository.addFavorites(userId, productId);
        }
    }

    @Override
    public List<Product> getProductsByUserId(Long userId) {
        List<Product> products = new ArrayList<>();
        for (Long id : userRepository.findProductsByUserId(userId)) {
            products.add(productRepository.findById(id).get());
        }
        return products;
    }

    @Override
    public void deleteFavorites(Long userId, Long productId) {
        userRepository.deleteFavoritesByUserId(userId,productId);
    }
}
