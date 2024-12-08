package org.unito.postgreserver;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Movie {
    @Id
    private Long id;
    private String title;
    private int releaseYear;
    private String tagline;
    private String description;
    private int durationInMinutes;
    private double rating;

    // Costruttori
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

    // Getter e Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }
    public String getTagline() { return tagline; }
    public void setTagline(String tagline) { this.tagline = tagline; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getDurationInMinutes() { return durationInMinutes; }
    public void setDurationInMinutes(int durationInMinutes) { this.durationInMinutes = durationInMinutes; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}

