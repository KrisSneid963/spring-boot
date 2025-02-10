package com.example.movies_7_1.controller;

import com.example.movies_7_1.dto.MovieDTO;
import com.example.movies_7_1.dto.MovieMapper;
import com.example.movies_7_1.model.Movie;
import com.example.movies_7_1.repository.ActorRepository;
import com.example.movies_7_1.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public MovieController(MovieService movieService, ActorRepository actorRepository) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // id with actors and screenings
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> movieOptional = movieService.getMovieById(id);
        return movieOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    //   movie with actors by one
//    @GetMapping("actors/{id}")
//    public ResponseEntity<List<Actor>> getActorsByMovieId(@PathVariable Long id) {
//        Optional<Movie> movieOptional = movieService.getMovieById(id);
//        return movieOptional.map(movie -> ResponseEntity.ok((List<Actor>) movie.getActors())).orElseGet(() -> ResponseEntity.notFound().build());
//
//    }
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
//    @GetMapping("/movies")
//    public List<Movie> getMovies() {
//        return movieService.findAllMovies();
//    }

    @PostMapping("/movies")
    public ResponseEntity<?> addMovie(@Valid @RequestBody MovieDTO movieDTO) {
        if (movieDTO == null) {
            return ResponseEntity.badRequest().body("Request body is missing!");
        }
        System.out.println("Received MovieDTO: " + movieDTO);

        Movie savedMovie = movieService.saveMovie(MovieMapper.toMovie(movieDTO));

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(savedMovie.getId())
                                .toUri())
                .body(MovieMapper.movieDTO(savedMovie));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        if (movieService.deleteMovie(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
