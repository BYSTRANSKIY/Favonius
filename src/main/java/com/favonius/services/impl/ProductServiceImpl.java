package com.favonius.services.impl;

import com.favonius.entities.Image;
import com.favonius.entities.Product;
import com.favonius.entities.enums.Category;
import com.favonius.repositories.ProductRepository;
import com.favonius.services.ImageService;
import com.favonius.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ImageService imageService;

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
    public Product addProduct(Product product, MultipartFile file) throws IOException {
        addImageToProduct(product, file);
        product.setAvailability(true);
        product.setDateOfCreated(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void addImageToProduct(Product product, MultipartFile file) throws IOException {
        if (file.getSize() != 0) {
            Image image = imageService.toImageEntity(file);
            image.setProduct(product);
            product.getImages().add(image);
        }
    }
}
