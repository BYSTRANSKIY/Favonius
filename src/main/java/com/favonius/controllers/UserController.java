package com.favonius.controllers;

import com.favonius.entities.User;
import com.favonius.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/registration")
    public String registration(Model model, Principal principal) {
        model.addAttribute("title", "Registration");
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "/registration";
        }
        boolean isUsernameExists = userService.isUsernameExists(user.getUsername());
        boolean isEmailExists = userService.isEmailExists(user.getEmail());
        if (isUsernameExists || isEmailExists) {
            if (isUsernameExists) {
                model.addAttribute("usernameError", "Username already exists");
            }
            if (isEmailExists) {
                model.addAttribute("emailError", "Email already exists");
            }
            return "registration";
        } else {
            userService.registerUser(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String login(Model model, Principal principal) {
        model.addAttribute("title", "Login");
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }
}
