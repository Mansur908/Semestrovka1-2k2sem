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
import ru.itis.demo.dto.AddProductsForm;
import ru.itis.demo.models.Product;
import ru.itis.demo.repositories.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ProductRepository productRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String getMainPage()  {
        return "admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String addProducts(Product form, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        if (!file.isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));

            form.setImage(resultFileName);
            productRepository.save(form);
            model.addAttribute("message","Товар добавлен");
            return "admin";
        }
        model.addAttribute("message","Товар не добавлен");
        return "admin";
    }
}
