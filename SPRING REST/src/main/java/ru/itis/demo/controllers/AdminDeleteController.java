package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.demo.models.Product;
import ru.itis.demo.services.intrfases.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminDeleteController {
    private final ProductService productService;

//    @ApiOperation(value ="Получение проектов")
//    @ApiResponses(value = {@ApiResponse(code = 200, message ="Проекты" , response = List.class )})
    @GetMapping("/admin/del")
    public List<Product> getPage()  {
        return productService.getProducts();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/admin/del/{id}")
    public List<Product> getPage(@PathVariable("id") Long id)  {
        if (!productService.containProduct(id)) {
            return productService.getProducts();
        }
        productService.deleteProduct(id);
        return productService.getProducts();
    }
}