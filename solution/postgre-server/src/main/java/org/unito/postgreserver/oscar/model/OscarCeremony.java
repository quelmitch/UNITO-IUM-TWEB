package org.unito.postgreserver.oscar.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "oscar_ceremony")
public class OscarCeremony {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;

    @Column(nullable = false, unique = true)
    private int year;

    @OneToMany(mappedBy = "oscarCeremony", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OscarNomination> nominations;
}
