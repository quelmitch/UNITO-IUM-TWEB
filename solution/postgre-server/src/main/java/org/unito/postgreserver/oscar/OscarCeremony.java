package org.unito.postgreserver.oscar;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "oscar_ceremony")
class OscarCeremony {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;

    @Column(nullable = false, unique = true)
    private int year;

    @OneToMany(mappedBy = "oscarCeremony", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OscarNomination> nominations;
}
