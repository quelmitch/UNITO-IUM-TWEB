package org.unito.postgreserver.utils;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SpecificationUtility {

    public static <T> Specification<T> equals(String field, Object value) {
        return (root, query, criteriaBuilder) ->
                value == null ? null : criteriaBuilder.equal(root.get(field), value);
    }

    public static <T> Specification<T> greaterThan(String field, Number value) {
        return (root, query, criteriaBuilder) ->
                value == null ? null : criteriaBuilder.gt(root.get(field), value);
    }

    public static <T> Specification<T> lessThan(String field, Number value) {
        return (root, query, criteriaBuilder) ->
                value == null ? null : criteriaBuilder.lt(root.get(field), value);
    }

    public static <T> Specification<T> notEqual(String field, Number value) {
        return (root, query, criteriaBuilder) ->
                value == null ? null : criteriaBuilder.notEqual(root.get(field), value);
    }

    public static <T> Specification<T> combine(List<Specification<T>> specifications) {
        return (root, query, criteriaBuilder) -> {
            if (specifications == null || specifications.isEmpty())
                return null;

            // Init with an always true value
            Specification<T> combined = Specification.where(null);
            for (Specification<T> specification : specifications) {
                combined = combined.and(specification);
            }

            return combined.toPredicate(root, query, criteriaBuilder);
        };
    }
}
