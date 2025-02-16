package techin.lt.cars.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RentalDTO(
        Long id,

        @NotNull
        Long carId,

        @NotNull
        Long userId,

        @NotNull
        LocalDate rentalStart,

        LocalDate rentalEnd,

        @NotNull
        BigDecimal price
) {
}
