package org.unito.postgreserver.crew.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Schema(description = "Filter DTO for searching crew members.")
public class CrewFilterDTO {

    @Schema(description = "Role of the crew member in the movie.", example = "Director", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String role;

    @Schema(description = "Name of the crew member.", example = "Christopher Nolan", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    @Schema(description = "Field by which to sort the results.", example = "id", defaultValue = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String sortBy = "id";
}
