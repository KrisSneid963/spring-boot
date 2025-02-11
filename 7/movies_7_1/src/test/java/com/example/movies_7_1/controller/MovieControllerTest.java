package com.example.movies_7_1.controller;

import com.example.movies_7_1.model.Movie;
import com.example.movies_7_1.security.SecurityConfig;
import com.example.movies_7_1.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = MovieController.class)
@Import(SecurityConfig.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovieService movieService;

    //movie nr 6 got by ordinary user
    @Test
    @WithMockUser(authorities = "SCOPE_ROLE_USER")
    void getMovie_whenAuthenticated_thenReturnMovie6_200_OK() throws Exception {
        // id6
        Movie movie = new Movie("Inception", "Christopher Nolan");
        given(movieService.getMovieById(6L)).willReturn(Optional.of(movie));  // Hardcoded ID 6

        //get request for movie6
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/6"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Inception"))
                .andExpect(jsonPath("$.director").value("Christopher Nolan"));

        Mockito.verify(movieService, times(1)).getMovieById(6L);
    }

    // moviei d7
    @Test
    @WithMockUser(authorities = "SCOPE_ROLE_USER")
    void getMovie_whenAuthenticated_thenReturnMovie7_200_OK() throws Exception {
        //id6
        Movie movie = new Movie("The Dark Knight", "Christopher Nolan");
        given(movieService.getMovieById(7L)).willReturn(Optional.of(movie));

        // get request for movie6
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The Dark Knight"))
                .andExpect(jsonPath("$.director").value("Christopher Nolan"));

        Mockito.verify(movieService, times(1)).getMovieById(7L);
    }

    // delete movie 6
    @Test
    @WithMockUser(authorities = "SCOPE_ROLE_ADMIN")
    void deleteMovie_whenAdminDelete_thenReturn204_NO_Content_FOR_6() throws Exception {
        Long movieId = 6L;

        given(movieService.deleteMovie(movieId)).willReturn(true);

        // when then: delete request
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movies/{id}", movieId))
                .andExpect(status().isNoContent());  // 204 No Content

        Mockito.verify(movieService, times(1)).deleteMovie(movieId);
    }

    // delete 7
    @Test
    @WithMockUser(authorities = "SCOPE_ROLE_ADMIN")
    void deleteMovie_whenAdminDelete_thenReturn204_NO_Content_For_7() throws Exception {
        Long movieId = 7L;

        given(movieService.deleteMovie(movieId)).willReturn(true);

        // when and then request delete  movie 7
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movies/{id}", movieId))
                .andExpect(status().isNoContent());  // 204 No Content

        Mockito.verify(movieService, times(1)).deleteMovie(movieId);
    }

    // Admin adds a new movie ( 201 Created)
    @Test
    @WithMockUser(authorities = "SCOPE_ROLE_ADMIN")
    void addMovie_whenAdminAddMovie_thenReturn201_CREATED() throws Exception {
        Movie movie = new Movie("Avatar", "James Cameron");
        given(movieService.saveMovie(ArgumentMatchers.any(Movie.class))).willReturn(movie);

        // When & Then: POST request to add a new movie
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(movie)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Avatar"))
                .andExpect(jsonPath("$.director").value("James Cameron"));

        Mockito.verify(movieService, times(1)).saveMovie(ArgumentMatchers.any(Movie.class));
    }
}
