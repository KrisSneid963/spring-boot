package techin.lt.cats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techin.lt.cats.model.Reservation;
import techin.lt.cats.model.User;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
}
