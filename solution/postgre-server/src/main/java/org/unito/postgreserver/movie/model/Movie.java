package org.unito.postgreserver.movie.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.unito.postgreserver.actor.model.Actor;
import org.unito.postgreserver.country.Country;
import org.unito.postgreserver.crew.Crew;
import org.unito.postgreserver.genre.Genre;
import org.unito.postgreserver.language.Language;
import org.unito.postgreserver.release.Release;
import org.unito.postgreserver.studio.Studio;
import org.unito.postgreserver.theme.Theme;

import java.util.List;

// JPA Annotations
@Table @Entity
// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Movie {
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    @Min(value = 1800, message = "Movie Year min value: 1800")
    @Max(value = 9999, message = "Movie Year max value: 9999")
    private Integer releaseYear;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String tagline;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = true)
    @Min(0) @Max(999)
    private Integer durationInMinutes;

    @Column(nullable = true)
    //@DecimalMin(value = "0", message = "Movie Rating min value: 0")
    //@DecimalMax(value = "5", message = "Movie Rating max value: 5")
    //@Digits(integer = 1, fraction = 2, message = "Movie Rating can have at most 1 integer digit and 2 fractional digits")
    private Double rating; // TODO: error in constraints

    @Column(nullable = true)
    private String posterLink;

    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Language> languages;

    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Genre> genres;

    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Actor> actors;

    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Country> countries;

    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Crew> crewMembers;

    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Release> releases;

    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Studio> studios;

    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Theme> themes;
}
