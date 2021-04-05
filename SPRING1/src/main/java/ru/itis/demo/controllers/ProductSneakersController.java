package ru.itis.demo.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.SearchForm;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.models.Product;
import ru.itis.demo.repositories.ProductRepository;
import ru.itis.demo.security.details.UserDetailsImpl;
import ru.itis.demo.services.intrfases.ProductSearchService;

import java.util.List;

@Controller
@RequestMapping("/sneakers")
@RequiredArgsConstructor
public class ProductSneakersController {
//    private final ProductRepository productRepository;
//
//    @GetMapping
//    public String getProduct1(Model model) {
//        List<Product> products = productRepository.findAllByType("sneakers");
//        model.addAttribute("products", products);
//        return "products";
//    }

    private final ProductSearchService productSearchService;

    //    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getUsersBySearchForm(@RequestBody SearchForm searchForm) {
        Page<Product> products = productSearchService.findAllByRequestBody(searchForm);
        System.out.println(products.toList().toString());
        return products.toList().toString();
    }

    //    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String getSearchPage() {
        return "products";
    }


}