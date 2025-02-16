package techin.lt.cats.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techin.lt.cats.dto.UserDTO;
import techin.lt.cats.dto.CatAdoptionDTO;
import techin.lt.cats.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUserDTO = userService.registerUser(userDTO);
        return ResponseEntity.ok(savedUserDTO);
    }

    @GetMapping("/{userId}/cats")
    public ResponseEntity<List<CatAdoptionDTO>> getUserCats(@PathVariable Long userId) {
        List<CatAdoptionDTO> userCats = userService.getUserCats(userId);
        return ResponseEntity.ok(userCats);
    }
}
