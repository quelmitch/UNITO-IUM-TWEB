package org.unito.postgreserver.studio.dto;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class StudioFilterDTO {
    String studio;

    String sortBy = "id";
}
