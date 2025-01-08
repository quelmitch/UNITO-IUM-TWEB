package org.unito.postgreserver.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

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

    @Schema(description = "List of actors in the movie.", example = "[\"Margot Robbie\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> actor;

    @Schema(description = "List of crew members in the movie.", example = "[\"Christopher Nolan\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> crew;

    @Schema(description = "List of genres the movie belongs to.", example = "[\"Action\", \"Thriller\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> genre;

    @Schema(description = "List of countries where the movie was produced.", example = "[\"United States\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> productionCountry;

    @Schema(description = "List of languages in which the movie was released.", example = "[\"English\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> language;

    @Schema(description = "List of countries where the movie was released.", example = "[\"United States\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> releaseCountry;

    @Schema(description = "List of studios that produced the movie.", example = "[\"Warner Bros.\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> studio;

    @Schema(description = "List of characters played by the actors in the movie.", example = "[\"Harley Quinn\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> character;

    @Schema(description = "List of audience ratings the movie has received.", example = "[\"PG-13\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> audienceRating;
}
