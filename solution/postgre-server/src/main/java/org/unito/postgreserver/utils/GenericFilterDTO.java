package org.unito.postgreserver.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

/**
 * Data Transfer Object (DTO) for generic filtering and pagination.
 * This class encapsulates parameters used for filtering data, including pagination, response type, and sorting.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Generic Filter DTO")
public class GenericFilterDTO {
    @Schema(description = "The page number to retrieve.", example = "0", defaultValue = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Min(0)
    private Integer page = 0;

    @Schema(description = "The maximum number of items to return per page.", example = "10", defaultValue = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Min(1)
    @Max(500)
    private Integer limit = 20;

    @Schema(description = "The type of response to return.", example = "FULL", defaultValue = "FULL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Type responseType = Type.BASIC;

    @Schema(description = "The sort order.", example = "ASC", defaultValue = "ASC", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Sort sort = Sort.ASC;

    @Schema(description = "The response type.", example = "FULL")
    public enum Type {
        @Schema(description = "Returns a minimal set of data.")
        BASIC,

        @Schema(description = "Returns all available data.")
        FULL
    }

    @Schema(description = "The sort order.", example = "ASC")
    public enum Sort {
        @Schema(description = "Ascending order.")
        ASC,

        @Schema(description = "Descending order.")
        DESC
    }
}
