package com.favonius.controllers;

import com.favonius.entities.enums.Category;
import com.favonius.services.ProductService;
import com.favonius.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title,
                           @RequestParam(name = "type", required = false) String type, Model model, Principal principal) {
        model.addAttribute("pageTitle", "Favonius");
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("products", productService.getProducts(title, type));
        return "products";
    }
}
