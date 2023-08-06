package com.favonius.services.impl;

import com.favonius.entities.Image;
import com.favonius.entities.Product;
import com.favonius.entities.enums.Category;
import com.favonius.repositories.ProductRepository;
import com.favonius.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts(String title, String category) {

        if (title != null && category != null && !title.isEmpty()) {
            if (category.equals("ALL")) {
                return productRepository.findByTitle(title);
            } else {
                return productRepository.findByTitleAndCategory(title, Category.valueOf(category));
            }
        } else {
            return productRepository.findAll();
        }
    }

    @Override
    public void addProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        addImageToProduct(product, file1);
        addImageToProduct(product, file2);
        addImageToProduct(product, file3);
        product.setDateOfCreated(LocalDateTime.now());
        productRepository.save(product);
    }

    private void addImageToProduct(Product product, MultipartFile file) throws IOException {
        if (file.getSize() != 0) {
            Image image = toImageEntity(product, file);
            product.getImages().add(image);
        }
    }

    private Image toImageEntity(Product product, MultipartFile file) throws IOException {
        Image image = new Image();
        image.setProduct(product);
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}
