package techin.lt.cats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techin.lt.cats.model.CatAdoption;
import techin.lt.cats.model.User;
import techin.lt.cats.repository.CatAdoptionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CatAdoptionService {

    @Autowired
    private CatAdoptionRepository catAdoptionRepository;

    // Get all adoptions
    public List<CatAdoption> getAllAdoptions() {
        return catAdoptionRepository.findAll();
    }

    // Get adoption by ID
    public Optional<CatAdoption> getAdoptionById(Long id) {
        return catAdoptionRepository.findById(id);
    }

    // Save a new cat adoption
    public CatAdoption saveAdoption(CatAdoption catAdoption) {
        return catAdoptionRepository.save(catAdoption);
    }

    // Get adoptions by user (adopter)
    public List<CatAdoption> getAdoptionsByUser(User adopter) {
        return catAdoptionRepository.findByAdopter(adopter); // Find adoptions for a specific user
    }
}
