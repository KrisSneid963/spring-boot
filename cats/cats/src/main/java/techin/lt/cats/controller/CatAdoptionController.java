package techin.lt.cats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techin.lt.cats.model.CatAdoption;
import techin.lt.cats.model.User;
import techin.lt.cats.service.CatAdoptionService;
import techin.lt.cats.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cat-adoptions")
public class CatAdoptionController {

    private final CatAdoptionService catAdoptionService;
    private final UserService userService;

    @Autowired
    public CatAdoptionController(CatAdoptionService catAdoptionService, UserService userService) {
        this.catAdoptionService = catAdoptionService;
        this.userService = userService;
    }

    // Get all adoptions
    @GetMapping
    public ResponseEntity<List<CatAdoption>> getAllAdoptions() {
        return ResponseEntity.ok(catAdoptionService.getAllAdoptions());
    }

    // Get adoption by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<CatAdoption>> getAdoptionById(@PathVariable Long id) {
        return ResponseEntity.ok(catAdoptionService.getAdoptionById(id));
    }

    // Create a new adoption
    @PostMapping
    public ResponseEntity<CatAdoption> saveAdoption(@RequestBody CatAdoption catAdoption) {
        return ResponseEntity.ok(catAdoptionService.saveAdoption(catAdoption));
    }

    // Get adoptions by user (adopter)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CatAdoption>> getAdoptionsByUser(@PathVariable Long userId) {
        // Retrieve the user by ID
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(catAdoptionService.getAdoptionsByUser(user));
    }
}
