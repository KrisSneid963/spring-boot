package com.example.movies_7_1.dto;

import com.example.movies_7_1.model.Movie;


import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {

    public static List<MovieDTO> mapToMovieDTOList(List<Movie> movies) {
        return movies.stream()
                .map(MovieMapper::movieDTO)
                .collect(Collectors.toList());
    }

    //for returning what you want to set
    public static MovieDTO movieDTO(Movie movie) {
        return new MovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getActors(),
                movie.getScreenings()

        );
    }


    public static Movie toMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setId(movieDTO.id());
        movie.setTitle(movieDTO.title());
        movie.setDirector(movieDTO.director());
        movie.setScreenings(movieDTO.screenings());
        movie.setActors(movieDTO.actors());

        return movie;
    }
}
