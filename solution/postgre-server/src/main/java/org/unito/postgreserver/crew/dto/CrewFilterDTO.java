package org.unito.postgreserver.crew.dto;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class CrewFilterDTO {
    String role;
    String name;

    String sortBy = "id";
}
