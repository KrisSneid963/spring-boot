package techin.lt.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techin.lt.club.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
