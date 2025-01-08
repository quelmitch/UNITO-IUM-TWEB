package org.unito.postgreserver.studio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Schema(description = "Filter DTO for searching studios.")
public class StudioFilterDTO {

    @Schema(description = "Name of the studio.", example = "Warner Bros.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String studio;

    @Schema(description = "Field by which to sort the results.", example = "id", defaultValue = "id")
    private String sortBy = "id";
}
