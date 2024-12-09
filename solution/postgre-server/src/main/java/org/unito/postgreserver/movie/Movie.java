package org.unito.postgreserver.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.unito.postgreserver.language.Language;

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

    //@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private Set<Language> subtitles_languages;
}

