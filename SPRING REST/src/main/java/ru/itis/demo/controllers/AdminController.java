package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.AddProductsForm;
import ru.itis.demo.models.Product;
import ru.itis.demo.services.intrfases.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin/addproduct")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Product>> getMainPage()  {
        return  ResponseEntity.ok(adminService.getProd());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<List<Product>> addProducts(@RequestBody AddProductsForm form) {
        return ResponseEntity.ok(adminService.addProd(form));
    }
}
