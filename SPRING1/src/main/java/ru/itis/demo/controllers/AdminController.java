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
import ru.itis.demo.services.intrfases.AdminService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String getMainPage()  {
        return "admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String addProducts(Product form, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        model.addAttribute("message",adminService.addProd(form,file));
        return "admin";
    }
}
