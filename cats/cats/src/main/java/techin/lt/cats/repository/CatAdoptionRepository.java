package techin.lt.cats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techin.lt.cats.model.CatAdoption;
import techin.lt.cats.model.User;

import java.util.List;

public interface CatAdoptionRepository extends JpaRepository<CatAdoption, Long> {
    List<CatAdoption> findByAdopter(User adopter);
}