package com.favonius.repositories;

import com.favonius.entities.Product;
import com.favonius.entities.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitleAndCategory(String title, Category category);

    List<Product> findByTitle(String title);
}
