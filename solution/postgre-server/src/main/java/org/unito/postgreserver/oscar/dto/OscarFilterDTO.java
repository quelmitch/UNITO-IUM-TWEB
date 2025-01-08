package org.unito.postgreserver.oscar.dto;

import lombok.*;

import java.util.List;

// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class OscarFilterDTO {
    private String sortBy = "id";

    private Integer numberCeremonyGT;
    private Integer numberCeremonyLT;

    private Integer yearCeremonyGT;
    private Integer yearCeremonyLT;

    private Integer yearMovie;

    private List<String> category;

    private List<String> nomineeName;

    private List<String> nomineeMovie;

    private Boolean isWinner;
}
