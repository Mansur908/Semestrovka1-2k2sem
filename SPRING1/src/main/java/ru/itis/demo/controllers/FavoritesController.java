package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.demo.dto.FavoritesForm;
import ru.itis.demo.models.Product;
import ru.itis.demo.security.details.UserDetailsImpl;
import ru.itis.demo.services.intrfases.FavoritesService;

import java.util.List;

@Controller
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoritesController {
    private final FavoritesService favoritesService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getSearchPage(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        List<Product> products = favoritesService.getProductsByUserId(user.getId());
        if (!products.isEmpty()){
            model.addAttribute("favorites", products);
        }
        return "favorites";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public String addFavorites(@AuthenticationPrincipal UserDetailsImpl user, FavoritesForm form)  {
        favoritesService.addFavorites(user.getId(),form.getProductId());
        return "redirect:/favorites";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete")
    public String deleteFavorites(@AuthenticationPrincipal UserDetailsImpl user, FavoritesForm form)  {
        favoritesService.deleteFavorites(user.getId(),form.getProductId());
        return "redirect:/favorites";
    }
}