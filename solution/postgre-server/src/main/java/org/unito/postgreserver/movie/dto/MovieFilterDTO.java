package org.unito.postgreserver.movie.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

// Lombok Annotations
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class MovieFilterDTO {
    private String sortBy = "id";

    private Integer durationGT;
    private Integer durationLT;
    private Integer releaseYearGT;
    private Integer releaseYearLT;
    private Double ratingGT;
    private Double ratingLT;

    private String actorName;
}
