package com.example.movies_7_1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UserDTO(
        @NotNull
        @Size(max = 80, message = "Maximum 80 characters")
        String username,

        @NotNull
        @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
        String password,

        Set<String> roles
) {
}
