package techin.lt.cats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techin.lt.cats.dto.CatAdoptionDTO;
import techin.lt.cats.dto.CatAdoptionMapper;
import techin.lt.cats.model.CatAdoption;
import techin.lt.cats.model.User;
import techin.lt.cats.service.CatAdoptionService;
import techin.lt.cats.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cat-adoptions")
public class CatAdoptionController {

    private final CatAdoptionService catAdoptionService;
    private final UserService userService;
    private final CatAdoptionMapper catAdoptionMapper;

    @Autowired
    public CatAdoptionController(CatAdoptionService catAdoptionService,
                                 UserService userService,
                                 CatAdoptionMapper catAdoptionMapper) {
        this.catAdoptionService = catAdoptionService;
        this.userService = userService;
        this.catAdoptionMapper = catAdoptionMapper;
    }


    @GetMapping
    public ResponseEntity<List<CatAdoptionDTO>> getAllAdoptions() {
        List<CatAdoptionDTO> adoptions = catAdoptionService.getAllAdoptions()
                .stream()
                .map(catAdoptionMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(adoptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatAdoptionDTO> getAdoptionById(@PathVariable Long id) {
        Optional<CatAdoption> catAdoption = catAdoptionService.getAdoptionById(id);
        return catAdoption.map(value -> ResponseEntity.ok(catAdoptionMapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CatAdoptionDTO> saveAdoption(@RequestBody CatAdoptionDTO catAdoptionDTO) {
        CatAdoption savedAdoption = catAdoptionService.saveAdoption(catAdoptionMapper.toEntity(catAdoptionDTO));
        return ResponseEntity.ok(catAdoptionMapper.toDto(savedAdoption));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CatAdoptionDTO>> getAdoptionsByUser(@PathVariable Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CatAdoptionDTO> adoptions = catAdoptionService.getAdoptionsByUser(user.getId())
                .stream()
                .map(catAdoptionMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(adoptions);
    }
}
