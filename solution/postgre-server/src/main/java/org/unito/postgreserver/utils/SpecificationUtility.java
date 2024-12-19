package org.unito.postgreserver.utils;

import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SpecificationUtility {

    public static <T> Specification<T> specEquals(String field, Object value) {
        return (root, _, criteriaBuilder) ->
                value == null ? null : criteriaBuilder.equal(root.get(field), value);
    }

    public static <T> Specification<T> specGreaterThan(String field, Number value) {
        return (root, _, criteriaBuilder) ->
                value == null ? null : criteriaBuilder.gt(root.get(field), value);
    }

    public static <T> Specification<T> specLessThan(String field, Number value) {
        return (root, _, criteriaBuilder) ->
                value == null ? null : criteriaBuilder.lt(root.get(field), value);
    }

    public static <T> Specification<T> specNotEquals(String field, Object value) {
        return (root, _, criteriaBuilder) ->
                value == null ? null : criteriaBuilder.notEqual(root.get(field), value);
    }

    public static <T> Specification<T> specIn(String field, List<?> values) {
        return (root, _, criteriaBuilder) ->
            values == null || values.isEmpty() ? null : criteriaBuilder.in(root.get(field)).value(values);
    }

    public static <T, J> Specification<T> specJoinAndEquals(String joinField, String targetField, Object value) {
        return (root, _, criteriaBuilder) -> value == null ? null : criteriaBuilder.equal(root.join(joinField, JoinType.INNER).get(targetField), value);
    }

    public static <T, J> Specification<T> specJoinAndGreaterThan(String joinField, String targetField, Number value) {
        return (root, _, criteriaBuilder) -> value == null ? null : criteriaBuilder.gt(root.join(joinField, JoinType.INNER).get(targetField), value);
    }

    public static <T, J> Specification<T> specJoinAndLessThan(String joinField, String targetField, Number value) {
        return (root, _, criteriaBuilder) -> value == null ? null : criteriaBuilder.lt(root.join(joinField, JoinType.INNER).get(targetField), value);
    }

    public static <T, J> Specification<T> specJoinAndNotEquals(String joinField, String targetField, Object value) {
        return (root, _, criteriaBuilder) -> value == null ? null : criteriaBuilder.notEqual(root.join(joinField, JoinType.INNER).get(targetField), value);
    }

    public static <T, J> Specification<T> specJoinIn(String joinField, String targetField, List<Object> values) {
        return (root, _, criteriaBuilder) ->
                values == null || values.isEmpty() ? null : criteriaBuilder.in(root.join(joinField, JoinType.INNER).get(targetField)).value(values);
    }

    public static <T> Specification<T> specCombine(List<Specification<T>> specifications) {
        return (root, query, criteriaBuilder) -> {
            if (specifications == null || specifications.isEmpty())
                return null;

            // Init with an always true value
            Specification<T> combined = Specification.where(null);
            for (Specification<T> specification : specifications)
                combined = combined.and(specification);

            return combined.toPredicate(root, query, criteriaBuilder);
        };
    }

}
