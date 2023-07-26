package com.favonius.controllers;

import com.favonius.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final UserService userService;

    @GetMapping("/")
    public String products(Model model, Principal principal) {
        model.addAttribute("pageTitle", "Favonius");
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "products";
    }
}
