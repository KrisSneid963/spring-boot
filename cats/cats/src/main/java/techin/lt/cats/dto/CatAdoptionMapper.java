package techin.lt.cats.dto;

import techin.lt.cats.dto.CatAdoptionDTO;
import techin.lt.cats.model.CatAdoption;
import techin.lt.cats.model.User;

public class CatAdoptionMapper {
    public static CatAdoptionDTO toCatAdoptionDTO(CatAdoption catAdoption) {
        return new CatAdoptionDTO(
                catAdoption.getId(),
                catAdoption.getCatName(),
                catAdoption.getStatus(),
                catAdoption.getApplicationDate(),
                catAdoption.getAdopter() != null ? catAdoption.getAdopter().getId() : null
        );
    }

    public static CatAdoption toCatAdoption(CatAdoptionDTO catAdoptionDTO, User adopter) {
        if (adopter == null) {
            throw new IllegalArgumentException("Adopter cannot be null");
        }
        return new CatAdoption(
                catAdoptionDTO.id(),
                catAdoptionDTO.catName(),
                catAdoptionDTO.status(),
                catAdoptionDTO.applicationDate(),
                adopter
        );
    }
}