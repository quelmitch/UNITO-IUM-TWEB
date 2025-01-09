package org.unito.postgreserver.studio.dto;

import lombok.*;
import org.unito.postgreserver.studio.model.Studio;
import org.unito.postgreserver.studio.model.StudioType;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class StudioBasicDTO implements StudioType {
    private String studio;
    private Long movieId;

    public static StudioBasicDTO toDTO(Studio studio) {
        StudioBasicDTO dto = new StudioBasicDTO();
        dto.setStudio(studio.getStudio());
        dto.setMovieId(studio.getMovieId().getId());
        return dto;
    }
}
