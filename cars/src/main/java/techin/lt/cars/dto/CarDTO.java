package techin.lt.cars.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CarDTO(
        Long id,

        @NotNull
        @Size(max = 100, message = "Maximum 100 characters")
        @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "Must start with an uppercase letter")
        String brand,

        @NotNull
        @Size(max = 100, message = "Maximum 100 characters")
        String model,

        @NotNull
        Integer year,

        String status


) {
}
