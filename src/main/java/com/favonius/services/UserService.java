package com.favonius.services;

import com.favonius.entities.User;

import java.security.Principal;

public interface UserService {

    void registerUser(User user);

    boolean isUsernameExists(String username);

    boolean isEmailExists(String email);

    User getUserByPrincipal(Principal principal);

    User findByEmail(String email);
}
