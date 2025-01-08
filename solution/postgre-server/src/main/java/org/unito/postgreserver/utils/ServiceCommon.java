package org.unito.postgreserver.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.unito.postgreserver.utils.GenericFilterDTO.Sort.*;

/**
 * This class provides utility methods for common service operations.
 */
public class ServiceCommon {
    /**
     * Creates a `Pageable` object based on the provided `GenericFilterDTO` and a sort by field.
     * Can be used in the default repository method findAll().
     *
     * @param genericFilter The `GenericFilterDTO` object containing pagination and sorting information.
     * @param sortByField The field to sort by.
     * @return A `Pageable` object representing the pagination and sorting criteria.
     */
    public static Pageable setPageable (GenericFilterDTO genericFilter, String sortByField) {
        Integer page = genericFilter.getPage();
        Integer limit = genericFilter.getLimit();
        GenericFilterDTO.Sort sortOrder = genericFilter.getSort();

        Sort.Order sorting = sortOrder == ASC ? Sort.Order.asc(sortByField) : Sort.Order.desc(sortByField);

        return PageRequest.of(page, limit, Sort.by(sorting));
    }

    /**
     * Creates a `Pageable` object based on the provided `GenericFilterDTO`. Sorting is not applied in this version.
     * Can be used in the default repository method findAll().
     *
     * @param genericFilter The `GenericFilterDTO` object containing pagination and sorting information.
     * @return A `Pageable` object representing the pagination and sorting criteria.
     */
    public static Pageable setPageable (GenericFilterDTO genericFilter) {
        Integer page = genericFilter.getPage();
        Integer limit = genericFilter.getLimit();

        return PageRequest.of(page, limit);
    }

    /**
     * Builds a response map for a service call.
     * This method takes a `GenericFilterDTO` object, the total number of pages, and a list of content objects.
     * It creates a `HashMap` and populates it with the following key-value pairs:
     *  - limit: The limit value from the `GenericFilterDTO`.
     *  - page: The page number from the `GenericFilterDTO`.
     *  - totalPages: The total number of pages.
     *  - content: The list of content objects.
     *
     * @param genericFilter The `GenericFilterDTO` object used for filtering.
     * @param totalPages The total number of pages available.
     * @param content The list of content objects to be included in the response.
     * @return A `Map` containing the service response data.
     */
    public static Map<String, Object> buildResponse(GenericFilterDTO genericFilter, int totalPages, List<?> content) {
        Map<String, Object> response = new HashMap<>();

        response.put("limit", genericFilter.getLimit());
        response.put("page", genericFilter.getPage());
        response.put("totalPages", totalPages);
        response.put("content", content);
        return response;
    }
}
