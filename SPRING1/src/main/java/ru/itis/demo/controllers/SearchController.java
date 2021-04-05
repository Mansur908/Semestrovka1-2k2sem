package ru.itis.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dto.SearchForm;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.security.details.UserDetailsImpl;

//@Controller
//@RequestMapping("/search")
//@RequiredArgsConstructor
//public class SearchController {
//    private final UserSearchService userSearchService;
//
//    //    @PreAuthorize("hasAuthority('ADMIN')")
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Page<UserDto> getUsersBySearchForm(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody SearchForm searchForm) {
//        return userSearchService.findAllByRequestBody(searchForm);
//    }
//
//    //    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping
//    public String getSearchPage() {
//        return "search";
//    }
//}