package org.unito.postgreserver.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Table
@Entity(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
    @SequenceGenerator(name = "movie_seq", sequenceName = "movie_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    @Min(1800) @Max(9999)
    private int releaseYear;

    @Column(nullable = true)
    private String tagline; // TODO: VARCHAR or TEXT ???

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = true)
    @Min(0) @Max(999)
    private int durationInMinutes;

    @Column(nullable = true)
    private double rating; //TODO: constraints


    public Movie() {}

    public Movie(Long id, String title, int releaseYear, String tagline, String description, int durationInMinutes, double rating) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.tagline = tagline;
        this.description = description;
        this.durationInMinutes = durationInMinutes;
        this.rating = rating;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

