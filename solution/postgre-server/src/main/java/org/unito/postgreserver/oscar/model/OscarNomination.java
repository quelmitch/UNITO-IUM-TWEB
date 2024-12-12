package org.unito.postgreserver.oscar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "oscar_nomination")
class OscarNomination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String movie;

    @Column(nullable = false)
    private String person;

    @Column(nullable = false)
    private boolean winner;

    @ManyToOne
    @JoinColumn(name = "ceremony_number", nullable = false)
    @JsonIgnore
    private OscarCeremony oscarCeremony;
}
