package org.unito.postgreserver.movie.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.unito.postgreserver.actor.Actor;
import org.unito.postgreserver.genre.Genre;
import org.unito.postgreserver.language.Language;

import java.util.List;

// JPA Annotations
@Table @Entity
// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
    @SequenceGenerator(name = "movie_seq", sequenceName = "movie_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    @Min(value = 1800, message = "Movie Year min value: 1800")
    @Max(value = 9999, message = "Movie Year max value: 9999")
    private int releaseYear;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String tagline;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = true)
    @Min(0) @Max(999)
    private int durationInMinutes;

    @Column(nullable = true)
    //@DecimalMin(value = "0", message = "Movie Rating min value: 0")
    //@DecimalMax(value = "5", message = "Movie Rating max value: 5")
    //@Digits(integer = 1, fraction = 2, message = "Movie Rating can have at most 1 integer digit and 2 fractional digits")
    private double rating; // TODO: error in constraints

    @Column(nullable = true)
    private String poster_link; // Store prefix without domain and add later

    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Language> languages;

    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Genre> genres;

    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Actor> actors;
}

