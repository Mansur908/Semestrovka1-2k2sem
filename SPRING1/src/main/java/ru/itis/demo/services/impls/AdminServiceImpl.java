package ru.itis.demo.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.demo.models.Product;
import ru.itis.demo.repositories.ProductRepository;
import ru.itis.demo.services.intrfases.AdminService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final ProductRepository productRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String addProd(Product form, MultipartFile file) throws IOException {
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
            return "Товар добавлен";
        }
        return "Товар не добавлен";
    }
}
