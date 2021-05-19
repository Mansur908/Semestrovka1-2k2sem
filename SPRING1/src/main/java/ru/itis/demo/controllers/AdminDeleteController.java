package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.DeleteProductForm;
import ru.itis.demo.services.intrfases.ProductService;

@Controller
@RequiredArgsConstructor
public class AdminDeleteController {
    private final ProductService productService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/delete")
    public String getPage(Model model) {
        model.addAttribute("json", productService.getProducts().toString());
        return "admin_delete";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/delete")
    public String deleteProducts(DeleteProductForm form, Model model) {
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
}