package ru.itis.demo.services.intrfases;


import org.springframework.data.domain.Page;
import ru.itis.demo.dto.SearchForm;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.models.Product;

public interface ProductSearchService {
    Page<Product> findAllByRequestBody(SearchForm searchForm);
}