package org.unito.postgreserver.oscar;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// JPA Annotations
@Table @Entity
// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class OscarNomination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ceremony_number")
    private OscarCeremony ceremony;

    @Column(nullable = false)
    private String category; //TODO: enum?

    @Column (nullable = false)
    private Integer movie_year;

    @Column(nullable = true)
    private String movie;

    @Column(nullable = true)
    private String person;

    @Column(nullable = false)
    private Boolean winner;
}
