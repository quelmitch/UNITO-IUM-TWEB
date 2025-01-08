package org.unito.postgreserver.crew.dto;

import lombok.*;
import org.unito.postgreserver.crew.model.Crew;
import org.unito.postgreserver.crew.model.CrewType;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class CrewBasicDTO implements CrewType {
    private String name;
    private String role;
    private Long movieId;

    public static CrewBasicDTO toDTO(Crew crew) {
        CrewBasicDTO dto = new CrewBasicDTO();
        dto.setName(crew.getName());
        dto.setRole(crew.getRole());
        dto.setMovieId(crew.getMovieId().getId());
        return dto;
    }
}
