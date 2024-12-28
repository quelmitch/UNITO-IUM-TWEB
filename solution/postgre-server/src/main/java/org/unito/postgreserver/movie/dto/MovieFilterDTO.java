package org.unito.postgreserver.movie.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class MovieFilterDTO {
    @Min(0)
    private Integer offset = 0;
    @Min(1)
    private Integer limit = 10;

    // What DTO to return
    private String responseType = "";

    private Integer durationGT;
    private Integer durationLT;
    private Integer releaseYearGT;
    private Integer releaseYearLT;
    private Integer ratingGT;
    private Integer ratingLT;
}
