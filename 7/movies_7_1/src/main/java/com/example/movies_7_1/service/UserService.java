package com.example.movies_7_1.service;

import com.example.movies_7_1.dto.UserDTO;
import com.example.movies_7_1.model.Role;
import com.example.movies_7_1.model.User;
import com.example.movies_7_1.repository.RoleRepository;
import com.example.movies_7_1.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UserDTO convertToDTO(User user) {
        @NotNull Set<Long> roleIds = user.getRoles().stream()
                .map(Role::getId)
                .collect(Collectors.toSet());

        return new UserDTO(user.getId(), user.getUsername(), null, roleIds);
    }

    public UserDTO saveUser(@Valid UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findByUsername(userDTO.username());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with this username already exists");
        }

        // Assign roles 1 ordinary and 2 admin
        Set<Role> roles = new HashSet<>();
        if (userDTO.roles().isEmpty()) {  // If no roles are provided, assign default "ROLE_USER"
            Role defaultRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Default role ROLE_USER not found"));
            roles.add(defaultRole);
        } else {
            for (Long roleName : userDTO.roles()) {
                Role role = roleRepository.findByName(String.valueOf(roleName))
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                roles.add(role);
            }
        }

        User user = new User(userDTO.username(), passwordEncoder.encode(userDTO.password()), roles);
        userRepository.save(user);

        return convertToDTO(user);
    }

    //admins can fetch
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::convertToDTO);
    }
}
