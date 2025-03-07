package techin.lt.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import techin.lt.cars.model.User;
import techin.lt.cars.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // Bean'as egzistuojas kontekste, dėl to galime
    // ir @Autowire
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/users")
    // Kai kursite patys, būtinai naudokite DTO!
    public ResponseEntity<User> addUser(@RequestBody User user) {

        // Slatpažodis yra šifruojamas prieš saugant į duomenų bazę
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);


        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(user.getId())
                                .toUri())
                .body(user);
    }
}
