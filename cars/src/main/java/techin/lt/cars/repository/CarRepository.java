package techin.lt.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techin.lt.cars.model.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByModel(String model);

    List<Car> findByStatus(String status);

    List<Car> findByBrandAndModel(String brand, String model);
}
