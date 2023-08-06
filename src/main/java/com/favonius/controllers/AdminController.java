package com.favonius.controllers;

import com.favonius.entities.Product;
import com.favonius.services.ProductService;
import com.favonius.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin";
    }

    @GetMapping("/admin/add-product")
    public String addProduct(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "add-product";
    }

    @PostMapping("/admin/add-product")
    public String addProduct(Product product,
                             @RequestParam("file1") MultipartFile file1,
                             @RequestParam("file2")MultipartFile file2,
                             @RequestParam("file3")MultipartFile file3) throws IOException {
        productService.addProduct(product, file1, file2, file3);
        return "redirect:/admin";
    }


}
