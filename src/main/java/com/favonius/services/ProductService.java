package com.favonius.services;

import com.favonius.entities.Product;
import com.favonius.entities.enums.Category;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface ProductService {
    List<Product> getProducts(String title, String category);

    void addProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException;
}
