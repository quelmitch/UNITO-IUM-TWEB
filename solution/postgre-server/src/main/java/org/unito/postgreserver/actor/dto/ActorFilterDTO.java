package org.unito.postgreserver.actor.dto;

import lombok.*;

// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ActorFilterDTO {
    String name;
    String role;

    String sortBy = "id";
}
