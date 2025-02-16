package techin.lt.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techin.lt.cars.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {

}