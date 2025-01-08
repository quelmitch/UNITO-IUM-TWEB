package org.unito.postgreserver.oscar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Schema(description = "Filter DTO for searching oscars.")
public class OscarFilterDTO {

    @Schema(description = "Field by which to sort the results.", example = "id", defaultValue = "id")
    private String sortBy = "id";

    @Schema(description = "Filter by the number of the ceremony greater than the specified value.", example = "75", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer numberCeremonyGT;

    @Schema(description = "Filter by the number of the ceremony less than the specified value.", example = "100", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer numberCeremonyLT;

    @Schema(description = "Filter by the year of the ceremony greater than the specified value.", example = "2000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer yearCeremonyGT;

    @Schema(description = "Filter by the year of the ceremony less than the specified value.", example = "2020", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer yearCeremonyLT;

    @Schema(description = "Filter by the year of the movie.", example = "2015", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer yearMovie;

    @Schema(description = "List of categories for the Oscar nominations.", example = "[\"ACTOR IN A LEADING ROLE\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> category;

    @Schema(description = "List of nominee names.", example = "[\"Rami Malek\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> nomineeName;

    @Schema(description = "List of movies nominated for the Oscar.", example = "[\"Bohemian Rhapsody\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> nomineeMovie;

    @Schema(description = "Indicates whether the nominee was a winner.", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean isWinner;
}
