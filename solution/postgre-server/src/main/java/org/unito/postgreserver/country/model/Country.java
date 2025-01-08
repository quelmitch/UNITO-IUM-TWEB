package org.unito.postgreserver.country.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.unito.postgreserver.movie.model.Movie;

// JPA Annotations
@Table @Entity
// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Country implements CountryType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String country;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonIgnore
    @JsonBackReference
    private Movie movieId;
}
