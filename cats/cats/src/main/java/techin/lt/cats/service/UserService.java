package techin.lt.cats.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import techin.lt.cats.dto.UserDTO;
import techin.lt.cats.model.User;
import techin.lt.cats.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO registerUser(UserDTO userDTO) {

        User user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

        UserDTO savedUserDTO = new UserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getPassword());

        return savedUserDTO;
    }

    // Find user by ID
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId); // Retrieve the user by ID
    }
}
