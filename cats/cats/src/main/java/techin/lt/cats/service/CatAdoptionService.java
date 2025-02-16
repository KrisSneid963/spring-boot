package techin.lt.cats.service;

import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.stereotype.Service;
import techin.lt.cats.model.CatAdoption;
import techin.lt.cats.model.User;
import techin.lt.cats.repository.CatAdoptionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CatAdoptionService {

    private final CatAdoptionRepository catAdoptionRepository;

    public CatAdoptionService(CatAdoptionRepository catAdoptionRepository) {
        this.catAdoptionRepository = catAdoptionRepository;
    }

    public List<CatAdoption> getAllAdoptions() {
        return catAdoptionRepository.findAll();
    }

    public Optional<CatAdoption> getAdoptionById(Long id) {
        return catAdoptionRepository.findById(id);
    }

    public CatAdoption saveAdoption(CatAdoption catAdoption) {
        return catAdoptionRepository.save(catAdoption);
    }

    public List<CatAdoption> getAdoptionsByUser(Long userId) {
        return catAdoptionRepository.findByUserId(userId);
    }
}

