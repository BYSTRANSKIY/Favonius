package com.favonius.controllers;

import com.favonius.entities.Product;
import com.favonius.services.ProductService;
import com.favonius.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("title", "Admin");
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin";
    }

    @GetMapping("/admin/add-product")
    public String addProduct(Model model, Principal principal) {
        model.addAttribute("title", "Admin");
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "add-product";
    }

    @PostMapping("/admin/add-product")
    public String addProduct(Product product, @RequestParam("file") MultipartFile file) throws IOException {
        product = productService.addProduct(product, file);
        return "redirect:/product/" + product.getId();
    }

    @PostMapping("/admin/product/add-image/{id}")
    public String addImage(@PathVariable Long id, MultipartFile file) throws IOException {
        Optional<Product> product = productService.getProduct(id);
        if (product.isPresent()) {
            productService.addImageToProduct(product.get(), file);
        }
        return "redirect:/product/" + id;
    }
}
