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

    @Min(1) @Column(nullable = false)
    Integer numberCeremony;

    @Min(1927) @Column(nullable = false)
    Integer yearCeremony;

    @Column(nullable = false)
    Integer yearMovie;

    @Column(nullable = false)
    String category;

    @Column(nullable = true, columnDefinition = "TEXT")
    String nomineeName;

    @Column(nullable = true)
    String nomineeMovie;

    @Column(nullable = false)
    Boolean isWinner;
}
