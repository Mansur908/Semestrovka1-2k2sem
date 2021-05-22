package ru.itis.demo.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.SearchForm;
import ru.itis.demo.models.Product;
import ru.itis.demo.services.intrfases.ProductSearchService;

@RestController
@RequestMapping("/user/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductSearchService productSearchService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Page<Product>> getUsersBySearchForm(@RequestBody SearchForm searchForm) {
        Page<Product> products = productSearchService.findAllByRequestBody(searchForm);
        return ResponseEntity.ok(products);
    }

}