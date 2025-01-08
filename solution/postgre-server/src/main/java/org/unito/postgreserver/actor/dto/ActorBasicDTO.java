package org.unito.postgreserver.actor.dto;

import lombok.*;
import org.unito.postgreserver.actor.model.Actor;
import org.unito.postgreserver.actor.model.ActorType;

// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ActorBasicDTO implements ActorType {
    private String name;
    private String role;

    public static ActorBasicDTO toDTO(Actor actor) {
        ActorBasicDTO dto = new ActorBasicDTO();
        dto.setName(actor.getName());
        dto.setRole(actor.getRole());
        return dto;
    }
}
