package techin.lt.cats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techin.lt.cats.model.CatAdoption;

import java.util.List;

public interface CatAdoptionRepository extends JpaRepository<CatAdoption, Long> {

    List<CatAdoption> findByUserId(Long userId); // 🔹 Fix: Change 'adopter' to 'user'
}
