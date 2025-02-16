package techin.lt.cats.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CatAdoptionDTO(
        Long id,

        @NotNull
        @Size(max = 60, message = "Maximum 60 characters")
        @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "Must start with an uppercase letter")
        String catName,

        @NotNull
        String status,

        java.time.LocalDate applicationDate

) {
}