package techin.lt.cats.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techin.lt.cats.dto.UserDTO;
import techin.lt.cats.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    //for user service
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUserDTO = userService.registerUser(userDTO);
        return ResponseEntity.ok(savedUserDTO);
    }

}
