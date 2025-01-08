package org.unito.postgreserver.utils;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

/**
 * Data Transfer Object (DTO) for generic filtering and pagination.
 * This class encapsulates parameters used for filtering data, including pagination, response type, and sorting.
 */
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class GenericFilterDTO {
    /**
     * The page number to retrieve. Defaults to 0 (first page).
     * Must be a non-negative integer.
     */
    @Min(0)             private Integer page = 0;

    /**
     * The maximum number of items to return per page. Defaults to 20.
     * Must be between 1 and 500 (inclusive).
     */
    @Min(1) @Max(500)   private Integer limit = 20;

    /**
     * The type of response to return (e.g., basic or full). Defaults to FULL.
     * See {@link Type} for available options.
     */
    private Type responseType = Type.FULL;

    /**
     * The sort order. Defaults to ASC (ascending).
     * See {@link Sort} for available options.
     */
    private Sort sort = Sort.ASC;

    /**
     * Enum representing the possible response types.
     * <ul>
     *     <li>BASIC: Returns a minimal set of data.</li>
     *     <li>FULL: Returns all available data.</li>
     * </ul>
     */
    public enum Type {
        BASIC,
        FULL
    }

    /**
     * Enum representing the possible sort orders.
     * <ul>
     *     <li>ASC: Ascending order.</li>
     *     <li>DESC: Descending order.</li>
     * </ul>
     */
    public enum Sort {
        ASC,
        DESC
    }
}



