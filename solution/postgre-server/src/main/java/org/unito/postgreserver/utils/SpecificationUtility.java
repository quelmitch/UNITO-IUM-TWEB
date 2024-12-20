package org.unito.postgreserver.utils;

import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Utility class for creating generic JPA Specifications.
 * <p>
 * This class provides static methods for creating various types of JPA Specifications using Criteria API.
 * Specifications can be used for filtering entities in JPA queries.
 */
public class SpecificationUtility {

    /**
     * Creates a specification that checks if a field is equal to a specific value.
     *
     * @param field The name of the field to check.
     * @param value The value to compare with the field value.
     * @param <T>   The type of the entity.
     * @return A Specification that checks for equality, or null if the value is null.
     */
    public static <T> Specification<T> equalsTo(String field, Object value) {
        return (root, _, criteriaBuilder) ->
            value == null ? null : criteriaBuilder.equal(root.get(field), value);
    }

    /**
     * Creates a specification that checks a joined entity field for equality with a specific value.
     * <p>
     * The generic type {@code J} is used for context and does not affect the method's behavior.
     *
     * @param joinField   The name of the field to join on.
     * @param targetField The name of the field in the joined entity to check.
     * @param value       The value to compare with the joined field value.
     * @param <T>         The type of the entity.
     * @param <J>         The type of the joined entity (for context only).
     * @return A Specification that checks for equality on a joined field, or null if the value is null.
     */
    public static <T, J> Specification<T> equalsTo(String joinField, String targetField, Object value) {
        return (root, _, criteriaBuilder) ->
            value == null ? null : criteriaBuilder.equal(root.join(joinField, JoinType.INNER).get(targetField), value);
    }

    /**
     * Creates a specification that checks if a field is greater than a specific value.
     *
     * @param field The name of the field to check.
     * @param value The value to compare with the field value.
     * @param <T>   The type of the entity.
     * @return A Specification that checks for greater than comparison, or null if the value is null.
     */
    public static <T> Specification<T> greaterThan(String field, Number value) {
        return (root, _, criteriaBuilder) ->
            value == null ? null : criteriaBuilder.gt(root.get(field), value);
    }

    /**
     * Creates a specification that checks a joined entity field for being greater than a specific value.
     * <p>
     * The generic type {@code J} is used for context and does not affect the method's behavior.
     *
     * @param joinField   The name of the field to join on.
     * @param targetField The name of the field in the joined entity to check.
     * @param value       The value to compare with the joined field value.
     * @param <T>         The type of the entity.
     * @param <J>         The type of the joined entity (for context only).
     * @return A Specification that checks for greater than on a joined field, or null if the value is null.
     */
    public static <T, J> Specification<T> greaterThan(String joinField, String targetField, Number value) {
        return (root, _, criteriaBuilder) ->
            value == null ? null : criteriaBuilder.gt(root.join(joinField, JoinType.INNER).get(targetField), value);
    }

    /**
     * Creates a specification that checks if a field is less than a specific value.
     *
     * @param field The name of the field to check.
     * @param value The value to compare with the field value.
     * @param <T>   The type of the entity.
     * @return A Specification that checks for less than comparison, or null if the value is null.
     */
    public static <T> Specification<T> lessThan(String field, Number value) {
        return (root, _, criteriaBuilder) ->
            value == null ? null : criteriaBuilder.lt(root.get(field), value);
    }

    /**
     * Creates a specification that checks a joined entity field for being less than a specific value.
     * <p>
     * The generic type {@code J} is used for context and does not affect the method's behavior.
     *
     * @param joinField   The name of the field to join on.
     * @param targetField The name of the field in the joined entity to check.
     * @param value       The value to compare with the joined field value.
     * @param <T>         The type of the entity.
     * @param <J>         The type of the joined entity (for context only).
     * @return A Specification that checks for less than on a joined field, or null if the value is null.
     */
    public static <T, J> Specification<T> lessThan(String joinField, String targetField, Number value) {
        return (root, _, criteriaBuilder) ->
            value == null ? null : criteriaBuilder.lt(root.join(joinField, JoinType.INNER).get(targetField), value);
    }

    /**
     * Creates a specification that checks if a field is not equal to a specific value.
     *
     * @param field The name of the field to check.
     * @param value The value to compare with the field value.
     * @param <T>   The type of the entity.
     * @return A Specification that checks for not equal comparison, or null if the value is null.
     */
    public static <T> Specification<T> notEqualsTo(String field, Object value) {
        return (root, _, criteriaBuilder) ->
            value == null ? null : criteriaBuilder.notEqual(root.get(field), value);
    }

    /**
     * Creates a specification that checks a joined entity field for not being equal to a specific value.
     * <p>
     * The generic type {@code J} is used for context and does not affect the method's behavior.
     *
     * @param joinField   The name of the field to join on.
     * @param targetField The name of the field in the joined entity to check.
     * @param value       The value to compare with the joined field value.
     * @param <T>         The type of the entity.
     * @param <J>         The type of the joined entity (for context only).
     * @return A Specification that checks for not equals on a joined field, or null if the value is null.
     */
    public static <T, J> Specification<T> notEqualsTo(String joinField, String targetField, Object value) {
        return (root, _, criteriaBuilder) ->
            value == null ? null : criteriaBuilder.notEqual(root.join(joinField, JoinType.INNER).get(targetField), value);
    }

    /**
     * Creates a specification that checks if a field value is included in a list of values.
     *
     * @param field  The name of the field to check.
     * @param values The list of values to compare with the field value.
     * @param <T>    The type of the entity.
     * @return A Specification that checks for inclusion in a list, or null if the list is null or empty.
     */
    public static <T> Specification<T> equalsIn(String field, List<?> values) {
        return (root, _, criteriaBuilder) ->
            values == null || values.isEmpty() ? null : criteriaBuilder.in(root.get(field)).value(values);
    }

    /**
     * Creates a specification that checks if a joined entity field value is included in a list of values.
     * <p>
     * The generic type {@code J} is used for context and does not affect the method's behavior.
     *
     * @param joinField   The name of the field to join on.
     * @param targetField The name of the field in the joined entity to check.
     * @param values      The list of values to compare with the joined field value.
     * @param <T>         The type of the entity.
     * @param <J>         The type of the joined entity (for context only).
     * @return A Specification that checks for inclusion in a list on a joined field, or null if the list is null or empty.
     */
    public static <T, J> Specification<T> equalsIn(String joinField, String targetField, List<Object> values) {
        return (root, _, criteriaBuilder) ->
            values == null || values.isEmpty() ?
                null :
                criteriaBuilder.in(root.join(joinField, JoinType.INNER).get(targetField)).value(values);
    }

    /**
     * Creates a specification that performs a case-insensitive "like" search on a given field.
     *
     * @param field The name of the field to check.
     * @param value The value to search for, using a "like" pattern (e.g., '%value%').
     * @param <T>   The type of the entity.
     * @return A Specification that performs a case-insensitive "like" search, or null if the value is null or empty.
     */
    public static <T> Specification<T> like(String field, String value) {
        return (root, _, criteriaBuilder) ->
            value == null || value.isEmpty() ?
                null :
                criteriaBuilder.like(criteriaBuilder.lower(root.get(field)), "%" + value.toLowerCase() + "%");
    }

    /**
     * Creates a specification that performs a case-insensitive "like" search on a field of a joined entity.
     * <p>
     * The generic type {@code J} is used for context and does not affect the method's behavior.
     *
     * @param field       The name of the field to join on.
     * @param targetField The name of the field in the joined entity to check.
     * @param value       The value to search for, using a "like" pattern (e.g., '%value%').
     * @param <T>         The type of the entity.
     * @param <J>         The type of the joined entity (for context only).
     * @return A Specification that performs a case-insensitive "like" search on a joined field, or null if the value is null or empty.
     */
    public static <T, J> Specification<T> like(String field, String targetField, String value) {
        return (root, _, criteriaBuilder) ->
            value == null || value.isEmpty() ?
                null :
                criteriaBuilder.like(
                    criteriaBuilder.lower(root.join(field, JoinType.INNER).get(targetField)),
                    "%" + value.toLowerCase() + "%"
                );
    }


    /**
     * Combines a list of specifications into a single specification using logical AND.
     *
     * @param specifications The list of specifications to combine.
     * @param <T>            The type of the entity.
     * @return A combined Specification, or null if the input list is null or empty.
     */
    public static <T> Specification<T> combineWithAnd(List<Specification<T>> specifications) {
        return (root, query, criteriaBuilder) -> {
            if (specifications == null || specifications.isEmpty())
                return null;

            Specification<T> combined = Specification.where(null); // Start with always true
            for (Specification<T> specification : specifications)
                combined = combined.and(specification);

            return combined.toPredicate(root, query, criteriaBuilder);
        };
    }
}
