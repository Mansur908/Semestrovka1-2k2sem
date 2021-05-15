package ru.itis.demo.services.intrfases;

import ru.itis.demo.models.Product;

import java.util.List;

public interface ProductService {
    //    String getProducts();
    List<Product> getProducts();

    boolean containProduct(Long id);

    void deleteProduct(Long id);

}
