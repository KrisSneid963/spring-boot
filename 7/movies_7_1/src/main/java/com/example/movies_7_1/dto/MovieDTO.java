package com.example.movies_7_1.dto;

import com.example.movies_7_1.model.Actor;
import com.example.movies_7_1.model.Screening;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

public record MovieDTO(
        Long id,

        @NotNull
        @Size(max = 150, message = "Maximum 150 characters")
        String title,

        @NotNull
        @Size(max = 100, message = "Maximum 100 characters")
        @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "Must start with an uppercase letter")
        String director,
        Set<Actor> actors,
        List<Screening> screenings
) {

}












