package com.example.movies_7_1.controller;

import com.example.movies_7_1.dto.MovieDTO;
import com.example.movies_7_1.dto.MovieMapper;
import com.example.movies_7_1.model.Movie;
import com.example.movies_7_1.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.findAllMovies();
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content if no movies
        }
        return ResponseEntity.ok(movies); // 200 OK if movies exist
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> movieOptional = movieService.getMovieById(id);
        return movieOptional
                .map(ResponseEntity::ok)  // 200 OK if movie found
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());  // 404 Not Found if movie not found
    }

    @PostMapping("/movies")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addMovie(@Valid @RequestBody MovieDTO movieDTO) {
        if (movieDTO == null) {
            return ResponseEntity.badRequest().body("Request body is missing!");  // 400 Bad Request if body is missing
        }

        Movie savedMovie = movieService.saveMovie(MovieMapper.toMovie(movieDTO));
        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(savedMovie.getId())
                                .toUri())
                .body(MovieMapper.movieDTO(savedMovie));  // 201 Created if movie added
    }

    @DeleteMapping("/movies/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        if (movieService.deleteMovie(id)) {
            return ResponseEntity.noContent().build();  // 204 No Content if movie deleted
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 Not Found if movie not found
    }

}
