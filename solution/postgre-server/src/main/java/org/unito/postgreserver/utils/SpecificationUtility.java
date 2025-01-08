package org.unito.postgreserver.utils;

import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
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
     * @param field The name of the field to compare with the value.
     * @param value The value to compare with the field value.
     * @param <T>   The type of the entity.
     * @return A Specification that checks for equality, or null if the value is null.
     */
    public static <T> Specification<T> equalsTo(String field, Comparable<?> value) {
        return (root, _, criteriaBuilder) ->
            value == null ? null : criteriaBuilder.equal(root.get(field), value);
    }

    /**
     * Creates a specification that checks a joined entity field for equality with a specific value.
     * <p>
     * The generic type {@code J} is used for context and does not affect the method's behavior.
     *
     * @param joinField   The name of the field to join on.
     * @param matchField  The name of the field in the joined entity to compare with the value.
     * @param value       The value to compare with the joined field value.
     * @param <T>         The type of the entity.
     * @param <J>         The type of the joined entity (for context only).
     * @return A Specification that checks for equality on a joined field, or null if the value is null.
     */
    public static <T, J> Specification<T> equalsTo(String joinField, String matchField, Comparable<?> value) {
        return (root, _, criteriaBuilder) ->
            value == null ? null : criteriaBuilder.equal(root.join(joinField, JoinType.INNER).get(matchField), value);
    }

    /**
     * Creates a specification that checks if a field is equal to any value in the provided list of values.
     *
     * @param field  The name of the field to compare with the values.
     * @param values The list of values to compare with the field.
     * @param <T>    The type of the entity.
     * @return A Specification that checks for equality with each value in the list, combined with AND logic.
     */
    public static <T> Specification<T> equalsTo(String field, List<? extends Comparable<?>> values) {
        if(values == null || values.isEmpty())
            return Specification.where(null);

        List<Specification<T>> specs = new ArrayList<>();
        for (Comparable<?> value : values)
            specs.add(equalsTo(field, value)); // Adding the equalsTo specification for each value in the list
        return combineWithAnd(specs); // Combine all the specifications with AND logic
    }

    /**
     * Creates a specification that checks a joined entity field for equality with all the values in the provided list.
     * <p>
     * The generic type {@code J} is used for context and does not affect the method's behavior.
     *
     * @param joinField The name of the field to join on.
     * @param field     The name of the field in the joined entity to compare with the values.
     * @param values    The list of values to compare with the joined field.
     * @param <T>       The type of the root entity.
     * @param <J>         The type of the joined entity (for context only).
     * @return A Specification that checks for equality with each value in the list, combined with AND logic, on the joined field.
     */
    public static <T, J> Specification<T> equalsTo(String joinField, String field, List<? extends Comparable<?>> values) {
        if(values == null || values.isEmpty())
            return Specification.where(null);
        List<Specification<T>> specs = new ArrayList<>();
        for (Comparable<?> value : values)
            specs.add(equalsTo(joinField, field, value));
        return combineWithAnd(specs);
    }

    /**
     * Creates a specification that checks if a field is greater than a specific value.
     *
     * @param field The name of the field to compare with the value.
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
     * @param matchField  The name of the field in the joined entity to compare with the value.
     * @param value       The value to compare with the joined field value.
     * @param <T>         The type of the entity.
     * @param <J>         The type of the joined entity (for context only).
     * @return A Specification that checks for greater than on a joined field, or null if the value is null.
     */
    public static <T, J> Specification<T> greaterThan(String joinField, String matchField, Number value) {
        return (root, _, criteriaBuilder) ->
            value == null ? null : criteriaBuilder.gt(root.join(joinField, JoinType.INNER).get(matchField), value);
    }

    /**
     * Creates a specification that checks if a field is less than a specific value.
     *
     * @param field The name of the field to compare with the value.
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
     * @param matchField  The name of the field in the joined entity to compare with the value.
     * @param value       The value to compare with the joined field value.
     * @param <T>         The type of the entity.
     * @param <J>         The type of the joined entity (for context only).
     * @return A Specification that checks for less than on a joined field, or null if the value is null.
     */
    public static <T, J> Specification<T> lessThan(String joinField, String matchField, Number value) {
        return (root, _, criteriaBuilder) ->
            value == null ? null : criteriaBuilder.lt(root.join(joinField, JoinType.INNER).get(matchField), value);
    }

    /**
     * Creates a specification that checks if a field is not equal to a specific value.
     *
     * @param field The name of the field to compare with the value.
     * @param value The value to compare with the field value.
     * @param <T>   The type of the entity.
     * @return A Specification that checks for not equal comparison, or null if the value is null.
     */
    public static <T> Specification<T> notEqualsTo(String field, Comparable<?> value) {
        return (root, _, criteriaBuilder) ->
            value == null ? null : criteriaBuilder.notEqual(root.get(field), value);
    }

    /**
     * Creates a specification that checks a joined entity field for not being equal to a specific value.
     * <p>
     * The generic type {@code J} is used for context and does not affect the method's behavior.
     *
     * @param joinField   The name of the field to join on.
     * @param matchField  The name of the field in the joined entity to compare with the value.
     * @param value       The value to compare with the joined field value.
     * @param <T>         The type of the entity.
     * @param <J>         The type of the joined entity (for context only).
     * @return A Specification that checks for not equals on a joined field, or null if the value is null.
     */
    public static <T, J> Specification<T> notEqualsTo(String joinField, String matchField, Comparable<?> value) {
        return (root, _, criteriaBuilder) ->
            value == null ? null : criteriaBuilder.notEqual(root.join(joinField, JoinType.INNER).get(matchField), value);
    }

    /**
     * Creates a specification that checks if a field value is included in a list of values.
     *
     * @param field  The name of the field to compare with the value.
     * @param values The list of values to compare with the field value.
     * @param <T>    The type of the entity.
     * @return A Specification that checks for inclusion in a list, or null if the list is null or empty.
     */
    public static <T> Specification<T> equalsIn(String field, List<? extends Comparable<?>> values) {
        return (root, _, criteriaBuilder) ->
            values == null || values.isEmpty() ? null : criteriaBuilder.in(root.get(field)).value(values);
    }

    /**
     * Creates a specification that checks if a joined entity field value is included in a list of values.
     * <p>
     * The generic type {@code J} is used for context and does not affect the method's behavior.
     *
     * @param joinField   The name of the field to join on.
     * @param matchField  The name of the field in the joined entity to compare with the value.
     * @param values      The list of values to compare with the joined field value.
     * @param <T>         The type of the entity.
     * @param <J>         The type of the joined entity (for context only).
     * @return A Specification that checks for inclusion in a list on a joined field, or null if the list is null or empty.
     */
    public static <T, J> Specification<T> equalsIn(String joinField, String matchField, List<? extends Comparable<?>> values) {
        return (root, _, criteriaBuilder) ->
            values == null || values.isEmpty() ?
                null :
                criteriaBuilder.in(root.join(joinField, JoinType.INNER).get(matchField)).value(values);
    }

    /**
     * Creates a specification that performs a case-insensitive "like" search on a given field.
     *
     * @param field The name of the field to compare with the value.
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
     * Creates a specification that performs a case-insensitive "like" search on a joinField of a joined entity.
     * <p>
     * The generic type {@code J} is used for context and does not affect the method's behavior.
     *
     * @param joinField   The name of the joinField to join on.
     * @param matchField  The name of the field in the joined entity to compare with the value.
     * @param value       The value to search for, using a "like" pattern (e.g., '%value%').
     * @param <T>         The type of the entity.
     * @param <J>         The type of the joined entity (for context only).
     * @return A Specification that performs a case-insensitive "like" search on a joined joinField, or null if the value is null or empty.
     */
    public static <T, J> Specification<T> like(String joinField, String matchField, String value) {
        return (root, _, criteriaBuilder) ->
            value == null || value.isEmpty() ?
                null :
                criteriaBuilder.like(
                    criteriaBuilder.lower(root.join(joinField, JoinType.INNER).get(matchField)),
                    "%" + value.toLowerCase() + "%"
                );
    }

    /**
     * Creates a specification that applies the DISTINCT keyword to the query.
     *
     * @param <T> The type of the entity.
     * @return A Specification that applies the DISTINCT keyword to the query.
     */
    public static <T> Specification<T> distinct() {
        return (_, query, _) -> {
            query.distinct(true);
            return null;
        };
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