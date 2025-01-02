package org.unito.postgreserver.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.unito.postgreserver.utils.GenericFilterDTO.Sort.*;

public class ServiceCommon {
    public static Pageable setPageable (GenericFilterDTO genericFilter, String sortBy) {
        Integer page = genericFilter.getPage();
        Integer limit = genericFilter.getLimit();
        GenericFilterDTO.Sort sortOrder = genericFilter.getSort();

        Sort.Order sorting = sortOrder == ASC ? Sort.Order.asc(sortBy) : Sort.Order.desc(sortBy);

        return PageRequest.of(page, limit, Sort.by(sorting));
    }

    public static Pageable setPageable (GenericFilterDTO genericFilter) {
        Integer page = genericFilter.getPage();
        Integer limit = genericFilter.getLimit();

        return PageRequest.of(page, limit);
    }

    public static Map<String, Object> buildResponse(GenericFilterDTO genericFilter, int totalPages, List<?> content) {
        Map<String, Object> response = new HashMap<>();

        response.put("limit", genericFilter.getLimit());
        response.put("page", genericFilter.getPage());
        response.put("totalPages", totalPages);
        response.put("content", content);
        return response;
    }
}
