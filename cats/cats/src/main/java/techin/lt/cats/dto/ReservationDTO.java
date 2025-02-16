package techin.lt.cats.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ReservationDTO(
        Long id,

        @NotNull
        LocalDate dateOfReservation,

        @NotNull
        @Size(max = 50, message = "Maximum 50 characters")
        String timeSlot,

        @NotNull
        Integer numGuests,

        @NotNull
        Long reservedById
) {
}