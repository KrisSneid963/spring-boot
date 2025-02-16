package techin.lt.cats.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techin.lt.cats.dto.UserDTO;
import techin.lt.cats.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Register user
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUserDTO = userService.registerUser(userDTO); // Simplified: Just register the user

        return ResponseEntity.ok(savedUserDTO);
    }

}
