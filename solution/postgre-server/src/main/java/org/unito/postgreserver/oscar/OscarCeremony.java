package org.unito.postgreserver.oscar;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


// JPA Annotations
@Table @Entity
// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class OscarCeremony {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;

    @Column(nullable = false)
    private Integer year;

    @OneToMany(mappedBy = "ceremony", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OscarNomination> nominations = new ArrayList<>();
}
