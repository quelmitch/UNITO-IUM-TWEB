package org.unito.postgreserver.oscar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

// JPA Annotations
@Table @Entity
// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Oscar implements OscarType{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0) @Column(nullable = false)
    Integer number_ceremony;

    @Min(1927) @Column(nullable = false)
    Integer year_ceremony;

    @Column(nullable = false)
    Integer year_movie;

    @Column(nullable = false)
    String category;

    @Column(nullable = true, columnDefinition = "TEXT")
    String nominee_name;

    @Column(nullable = true)
    String nominee_movie;

    @Column(nullable = false)
    Boolean is_winner;
}
