package ru.itis.demo.services.intrfases;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.demo.models.Product;

import java.io.IOException;

public interface AdminService {
    String addProd(Product form, MultipartFile file) throws IOException;
}
