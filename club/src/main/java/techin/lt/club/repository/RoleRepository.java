package techin.lt.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techin.lt.club.model.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
}

