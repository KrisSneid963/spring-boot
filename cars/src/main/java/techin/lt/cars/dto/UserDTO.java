package techin.lt.cars.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UserDTO(
        Long id,

        @NotNull
        @Size(max = 80, message = "Maximum 80 characters")
        String username,

        @NotNull
        @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
        String password,

        Set<Long> roles
) {
}
