package techin.lt.club.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import techin.lt.club.model.Role;
import techin.lt.club.model.User;
import techin.lt.club.repository.RoleRepository;
import techin.lt.club.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        List<Role> userRole = roleRepository.findByName("ROLE_USER");
        if (userRole.isEmpty()) {
            return ResponseEntity.badRequest().body("Default role ROLE_USER not found");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(userRole.get(0)));

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }
}
