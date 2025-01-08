package org.unito.postgreserver.actor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Schema(description = "Filter DTO for searching actors.")
public class ActorFilterDTO {

    @Schema(description = "Name of the actor.", example = "Robert Downey Jr.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    @Schema(description = "Role of the actor in the movie.", example = "Iron Man",  requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String role;

    @Schema(description = "Field by which to sort the results.", example = "id", defaultValue = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String sortBy = "id";
}