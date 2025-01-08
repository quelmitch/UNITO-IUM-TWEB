package org.unito.postgreserver.release.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Schema(description = "Filter DTO for searching releases.")
public class ReleaseFilterDTO {

    @Schema(description = "Field by which to sort the results.", example = "id", defaultValue = "id")
    private String sortBy = "id";

    @Schema(description = "List of countries for which the release is available.", example = "[\"Italy\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> country;

    @Schema(description = "List of distribution formats of the release.", example = "[\"Theatrical\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> distributionFormat;

    @Schema(description = "List of ratings for the release.", example = "[\"VM14\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> rating;
}
