package ru.itis.demo.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.demo.dto.AddProductsForm;
import ru.itis.demo.models.Product;
import ru.itis.demo.repositories.ProductRepository;
import ru.itis.demo.services.intrfases.AdminService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getProd(){
        return productRepository.findAll();
    }

    @Override
    public List<Product> addProd(AddProductsForm form){
        Product product = new Product();
        product.setImage(null);
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setText(form.getText());
        product.setType(form.getType());

        productRepository.save(product);
        return productRepository.findAll();
    }
}
