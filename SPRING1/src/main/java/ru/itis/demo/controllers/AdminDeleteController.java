package ru.itis.demo.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.demo.dto.DeleteProductForm;
import ru.itis.demo.models.Product;
import ru.itis.demo.repositories.ProductRepository;
import ru.itis.demo.services.intrfases.ProductService;

import javax.annotation.security.PermitAll;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

//@RequestMapping("/admindel")
@Controller
@RequiredArgsConstructor
public class AdminDeleteController {
    private final ProductService productService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admindel")
    public String getPage(Model model) {
        model.addAttribute("json", productService.getProducts().toString());
        return "admin_delete";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admindel")
    public String deleteProducts(DeleteProductForm form, Model model) throws IOException {
        if (!productService.containProduct(form.getId())) {
            model.addAttribute("message", "Не удалено");
            model.addAttribute("json", productService.getProducts().toString());
            return "admin_delete";
        }
        productService.deleteProduct(form.getId());
        model.addAttribute("message", "Удалено");
        model.addAttribute("json", productService.getProducts().toString());
        return "admin_delete";
    }

    @ApiOperation(value = "Получение проектов")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Проекты", response = List.class)})
    @ResponseBody
    @GetMapping("/restadmindel")
    public List<Product> getPage() {
        return productService.getProducts();
    }

    //    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    @DeleteMapping("/restadmindel/{id}")
    public List<Product> getPage(@PathVariable("id") Long id) {
        if (!productService.containProduct(id)) {
            return productService.getProducts();
        }
        productService.deleteProduct(id);
        return productService.getProducts();
    }
}