package ru.itis.demo.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.SearchForm;
import ru.itis.demo.models.Product;
import ru.itis.demo.services.intrfases.ProductSearchService;


@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductSearchService productSearchService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getUsersBySearchForm(@RequestBody SearchForm searchForm) {
        Page<Product> products = productSearchService.findAllByRequestBody(searchForm);
        return products.toList().toString();
    }

    @GetMapping
    public String getSearchPage() {
        return "products";
    }
}