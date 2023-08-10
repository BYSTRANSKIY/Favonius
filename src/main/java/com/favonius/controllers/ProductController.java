package com.favonius.controllers;

import com.favonius.entities.Product;
import com.favonius.services.ProductService;
import com.favonius.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title,
                           @RequestParam(name = "type", required = false) String type, Model model, Principal principal) {
        model.addAttribute("title", "Favonius");
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("products", productService.getProducts(title, type));
        return "products";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable Long id, Model model, Principal principal) {
        Optional<Product> product = productService.getProduct(id);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("title", "Product");
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product";
        }
        return "products";
    }
}
