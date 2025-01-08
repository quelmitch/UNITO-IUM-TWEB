package org.unito.postgreserver.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Schema(description = "Filter DTO for searching movies.")
public class MovieFilterDTO {

    @Schema(description = "Field by which to sort the results.", example = "id", defaultValue = "id")
    private String sortBy = "id";

    @Schema(description = "Filter movies with a runtime greater than the specified value.", example = "100", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer runtimeGT;

    @Schema(description = "Filter movies with a runtime less than the specified value.", example = "120", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer runtimeLT;

    @Schema(description = "Filter movies released after the specified year.", example = "2010", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer releaseYearGT;

    @Schema(description = "Filter movies released before the specified year.", example = "2024", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer releaseYearLT;

    @Schema(description = "Filter movies with a rating greater than the specified value.", example = "3.1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Double ratingGT;

    @Schema(description = "Filter movies with a rating less than the specified value.", example = "4.5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Double ratingLT;

    @Schema(description = "List of actors in the movie.", example = "Margot Robbie", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> actor;

    private List<String> crew;

    private List<String> genre;

    private List<String> productionCountry;

    private List<String> language;

    private List<String> releaseCountry;

    private List<String> studio;

    private List<String> character;

    private List<String> audienceRating;


}
