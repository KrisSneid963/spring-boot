package com.example.movieA.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "screenings")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "movie_id", nullable = false)
//
//    private Movie movie;

    @Column(nullable = false)
    private LocalDateTime screeningDate;

    @Column(nullable = false, length = 255)
    private String location;

    public Screening() {
    }

    public Screening(LocalDateTime screeningDate, String location) {
        this.screeningDate = screeningDate;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalDateTime getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(LocalDateTime screeningDate) {
        this.screeningDate = screeningDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
