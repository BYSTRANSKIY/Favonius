package com.favonius.services;

import com.favonius.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProducts(String title, String category);

    Product addProduct(Product product, MultipartFile file) throws IOException;

    Optional<Product> getProduct(Long id);

    void addImageToProduct(Product product, MultipartFile file) throws IOException;
}
