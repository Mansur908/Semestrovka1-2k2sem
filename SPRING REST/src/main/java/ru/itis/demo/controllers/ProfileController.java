package ru.itis.demo.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.omg.IOP.ServiceContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.demo.models.User;
import ru.itis.demo.details.UserDetailsImpl;
import ru.itis.demo.services.intrfases.UsersService;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final UsersService usersService;

    @ApiOperation(value = "Профиль пользователя")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/profile")
    public ResponseEntity<User> getProfilePage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        return ResponseEntity.ok(usersService.getUser(userDetails.getUsername()));
    }
}