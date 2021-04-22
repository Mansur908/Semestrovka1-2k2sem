package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.demo.dto.DeleteProductForm;
import ru.itis.demo.models.Product;
import ru.itis.demo.repositories.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/admindel")
@RequiredArgsConstructor
public class AdminDeleteController {
    private final ProductRepository productRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String getPage(Model model)  {
        model.addAttribute("json", productRepository.findAll().toString());
        return "admin_delete";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String deleteProducts(DeleteProductForm form, Model model) throws IOException {
        if (!productRepository.findById(form.getId()).isPresent()) {
            model.addAttribute("message", "Не удалено");
            model.addAttribute("json", productRepository.findAll().toString());
            return "admin_delete";
        }
        productRepository.deleteById(form.getId());
        model.addAttribute("message","Удалено");
        model.addAttribute("json", productRepository.findAll().toString());
        return "admin_delete";
}
}