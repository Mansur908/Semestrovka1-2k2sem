package ru.itis.demo.services.intrfases;


import org.springframework.data.domain.Page;
import ru.itis.demo.dto.SearchForm;
import ru.itis.demo.dto.UserDto;

public interface UserSearchService {
    Page<UserDto> findAllByRequestBody(SearchForm searchForm);
}