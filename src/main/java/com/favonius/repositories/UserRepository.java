package com.favonius.repositories;

import com.favonius.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
