package com.favonius.services.impl;

import com.favonius.entities.User;
import com.favonius.entities.enums.Authority;
import com.favonius.repositories.UserRepository;
import com.favonius.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) {
            return new User();
        }
        return userRepository.findByEmail(principal.getName());
    }

    @Override
    public void registerUser(User user) {
        user.setActive(true);
        user.setDateOfCreated(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getAuthorities().add(Authority.USER);
        userRepository.save(user);
    }

    @Override
    public boolean isUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
