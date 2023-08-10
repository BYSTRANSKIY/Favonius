package com.favonius.entities;

import com.favonius.entities.enums.Authority;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)?$", message = "Only Latin characters")
    private String username;

    @Size(min = 3, max = 30, message = "First name must be between 3 and 30 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Only Latin characters")
    private String firstName;

    @Size(min = 3, max = 30, message = "Last name must be between 3 and 30 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Only Latin characters")
    private String lastName;

    @Size(min = 10, max = 13, message = "Invalid phone number")
    @Pattern(regexp = "^[\\d]+$", message = "Only number")
    private String phoneNumber;

    @NotNull
    @Email(message = "Invalid email")
    private String email;

    @NotNull
    @Size(min = 8, message = "Password must be 8 characters or longer")
//    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]+$",
//            message = "Password must have at least 1 number, 1 uppercase letter and 1 lowercase letter")
    @Column(length = 1000)
    private String password;

    private boolean active;

    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Authority> authorities = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Image> images = new ArrayList<>();

    private LocalDateTime dateOfCreated;

    public boolean isAdmin() {
        return authorities.contains(Authority.ADMIN);
    }
}
