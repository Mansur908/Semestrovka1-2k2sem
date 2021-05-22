package ru.itis.demo.services.intrfases;

import ru.itis.demo.dto.AddProductsForm;
import ru.itis.demo.models.Product;

import java.util.List;

public interface AdminService {
    List<Product> addProd(AddProductsForm form);

    List<Product> getProd();
}
