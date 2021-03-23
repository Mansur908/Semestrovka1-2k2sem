package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.demo.models.Product;
import ru.itis.demo.repositories.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/tshorts")
@RequiredArgsConstructor
public class ProductTshortController {
    private final ProductRepository productRepository;

    @GetMapping
    public String getProduct1(Model model) {
        List<Product> products = productRepository.findAllByType("tshort");
        model.addAttribute("products", products);
        return "products";
    }
}