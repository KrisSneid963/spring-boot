package techin.lt.cats.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import techin.lt.cats.dto.CatAdoptionDTO;
import techin.lt.cats.dto.CatAdoptionMapper;
import techin.lt.cats.dto.UserDTO;
import techin.lt.cats.model.CatAdoption;
import techin.lt.cats.model.User;
import techin.lt.cats.repository.CatAdoptionRepository;
import techin.lt.cats.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CatAdoptionRepository catAdoptionRepository;
    private final CatAdoptionMapper catAdoptionMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       CatAdoptionRepository catAdoptionRepository,
                       CatAdoptionMapper catAdoptionMapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.catAdoptionRepository = catAdoptionRepository;
        this.catAdoptionMapper = catAdoptionMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<CatAdoptionDTO> getUserCats(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CatAdoption> adoptions = catAdoptionRepository.findByUserId(user.getId());

        return adoptions.stream()
                .map(catAdoptionMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO registerUser(UserDTO userDTO) {

        if (userRepository.findByUsername(userDTO.username()).isPresent()) {
            throw new RuntimeException("Username already taken!");
        }

        User user = new User();
        user.setUsername(userDTO.username());
        //hash
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        User savedUser = userRepository.save(user);

        return new UserDTO(savedUser.getId(), savedUser.getUsername(), passwordEncoder.toString());
    }
}
