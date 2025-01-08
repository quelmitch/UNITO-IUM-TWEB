package org.unito.postgreserver.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;

// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Schema(description = "Filter DTO for searching movies.")
public class MovieFilterDTO {

    @Schema(description = "Field by which to sort the results.", example = "id", defaultValue = "id")
    private String sortBy = "id";

    @Schema(description = "Filter movies with a runtime greater than the specified value.", example = "120", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer runtimeGT;

    @Schema(description = "Filter movies with a runtime less than the specified value.", example = "150", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer runtimeLT;

    @Schema(description = "Filter movies released after the specified year.", example = "2000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer releaseYearGT;

    @Schema(description = "Filter movies released before the specified year.", example = "2020", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer releaseYearLT;

    @Schema(description = "Filter movies with a rating greater than the specified value.", example = "7.5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Double ratingGT;

    @Schema(description = "Filter movies with a rating less than the specified value.", example = "9.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Double ratingLT;

    //TODO
    @Schema(description = "List of actors in the movie.", example = "[\"Robert Downey Jr.\", \"Chris Hemsworth\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> actor;
}
