package techin.lt.cats.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techin.lt.cats.model.Role;
import techin.lt.cats.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findByName(String roleName) {
        return Optional.ofNullable(roleRepository.findByName(roleName));
    }

    public Optional<Role> findById(Long roleId) {
        return roleRepository.findById(roleId);
    }
}
