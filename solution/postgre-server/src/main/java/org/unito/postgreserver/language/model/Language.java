package org.unito.postgreserver.language.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.unito.postgreserver.movie.model.Movie;

@Table @Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Language implements LanguageType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonIgnore
    private Movie movieId;

}
