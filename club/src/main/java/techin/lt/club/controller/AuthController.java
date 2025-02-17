package techin.lt.club.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import techin.lt.club.model.Role;
import techin.lt.club.model.User;
import techin.lt.club.repository.RoleRepository;
import techin.lt.club.service.UserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<Role> userRole = roleRepository.findById(1L);

        Set<Role> assignedRoles = new HashSet<>();
        userRole.ifPresent(assignedRoles::add);

        user.setRoles(assignedRoles);

        userService.saveUser(user);

        return ResponseEntity.ok("User created successfully!");
    }
}
