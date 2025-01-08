package org.unito.postgreserver.country.model;

/**
 * Marker interface for country types.
 * <p>
 * This interface is used to provide type safety for return types of `Country` objects (Entity and DTOs).
 * Implementing this interface ensures that only valid country types can be used.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * public class Country implements CountryType {
 *     // ... implementation
 * }
 *
 * // Only Country types allowed
 * List<? extends CountryType> countries = ...;
 * }</pre>
 *
 * @see Country
 */
public interface CountryType {
}
