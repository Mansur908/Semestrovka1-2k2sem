package ru.itis.demo.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.demo.dto.SearchForm;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.models.Product;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.ProductRepository;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.services.intrfases.ProductSearchService;

@Service
@RequiredArgsConstructor
public class ProductSearchServiceImpl implements ProductSearchService {
    private final ProductRepository productRepository;

    @Override
    public Page<Product> findAllByRequestBody(SearchForm searchForm) {
        PageRequest pageRequest = PageRequest.of(searchForm.getPage() - 1, searchForm.getSize() , Sort.unsorted());
        Page<Product> productList = productRepository.findAll(searchForm.getName(), pageRequest);
        return productList.map(Product::from);
    }
}
