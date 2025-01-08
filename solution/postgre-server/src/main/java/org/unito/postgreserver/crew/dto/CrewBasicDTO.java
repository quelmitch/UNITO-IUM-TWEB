package org.unito.postgreserver.crew.dto;

import lombok.*;
import org.unito.postgreserver.crew.model.Crew;
import org.unito.postgreserver.crew.model.CrewType;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class CrewBasicDTO implements CrewType {
    private String name;

    public static CrewBasicDTO toDTO(Crew crew) {
        CrewBasicDTO dto = new CrewBasicDTO();
        crew.setName(crew.getName());
        return dto;
    }
}
