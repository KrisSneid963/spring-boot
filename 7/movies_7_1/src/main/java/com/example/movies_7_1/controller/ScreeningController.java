package com.example.movies_7_1.controller;

import com.example.movies_7_1.model.Screening;
import com.example.movies_7_1.model.Movie;
import com.example.movies_7_1.service.ScreeningService;
import com.example.movies_7_1.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ScreeningController {

    private final ScreeningService screeningService;
    private final MovieService movieService;

    @Autowired
    public ScreeningController(ScreeningService screeningService, MovieService movieService) {
        this.screeningService = screeningService;
        this.movieService = movieService;
    }

    @GetMapping("/screenings")
    public ResponseEntity<List<Screening>> getAllScreenings() {
        return ResponseEntity.ok(screeningService.getAllScreenings());
    }

    @GetMapping("/screenings/{id}")
    public ResponseEntity<Screening> getScreeningById(@PathVariable Long id) {
        return screeningService.getScreeningById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //new screening with error message
    @PostMapping("/screenings")
    public ResponseEntity<?> addScreening(@RequestBody Map<String, Object> requestData) {
        Long movieId = ((Number) requestData.get("movieId")).longValue();
        Optional<Movie> movieOptional = movieService.getMovieById(movieId);


        Screening screening = new Screening();

        screening.setScreeningDate(LocalDateTime.parse((String) requestData.get("screeningDate")));
        screening.setLocation((String) requestData.get("location"));

        Screening savedScreening = screeningService.saveScreening(screening);
        return ResponseEntity.ok(savedScreening);
    }

    // update
    @PutMapping("/screenings/{id}")
    public ResponseEntity<Screening> updateScreening(@PathVariable Long id, @RequestBody Screening updatedScreening) {
        try {
            Screening savedScreening = screeningService.updateScreening(id, updatedScreening);
            return ResponseEntity.ok(savedScreening);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/screenings/{id}")
    public ResponseEntity<Void> deleteScreening(@PathVariable Long id) {
        if (screeningService.deleteScreening(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
