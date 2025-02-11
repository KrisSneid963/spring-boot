package com.example.movies_7_1.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 50)
    private String director;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private List<Screening> screenings = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movies_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors = new HashSet<>();

    // Default constructor
    public Movie() {
    }


    public Movie(String title, String director) {
        this.title = title;
        this.director = director;
        this.actors = new HashSet<>();
        this.screenings = new ArrayList<>();
    }

    public Movie(String title, String director, Set<Actor> actors, List<Screening> screenings) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.screenings = screenings;
    }

    public Long getId() {
        return id;
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    public String getDirector() {
        return director;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }
}
