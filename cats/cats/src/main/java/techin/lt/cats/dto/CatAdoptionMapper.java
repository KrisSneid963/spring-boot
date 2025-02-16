package techin.lt.cats.dto;

import org.springframework.stereotype.Component;
import techin.lt.cats.model.CatAdoption;

@Component
public class CatAdoptionMapper {

    public CatAdoptionDTO toDto(CatAdoption catAdoption) {
        return new CatAdoptionDTO(
                catAdoption.getId(),
                catAdoption.getCatName(),  // ✅ String type
                catAdoption.getStatus(),   // ✅ String type
                catAdoption.getApplicationDate() // ✅ LocalDate type
        );
    }

    public CatAdoption toEntity(CatAdoptionDTO dto) {
        CatAdoption catAdoption = new CatAdoption();
        catAdoption.setCatName(dto.catName());
        catAdoption.setStatus(dto.status());
        catAdoption.setApplicationDate(dto.applicationDate());
        return catAdoption;
    }
}
