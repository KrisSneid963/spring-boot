package techin.lt.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techin.lt.club.model.RunningEvent;

import java.util.Optional;

public interface RunningEventRepository extends JpaRepository<RunningEvent, Long> {
    Optional<RunningEvent> findByName(String name);
}