package techin.lt.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techin.lt.club.model.Registrations;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registrations, Long> {
    List<Registrations> findByUserId(Long userId);
}