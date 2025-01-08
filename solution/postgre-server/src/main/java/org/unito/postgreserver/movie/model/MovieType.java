package org.unito.postgreserver.movie.model;

/**
 * Marker interface for movie types.
 * <p>
 * This interface is used to provide type safety for return types of `Movie` objects (Entity and DTOs).
 * Implementing this interface ensures that only valid movie types can be used.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * public class MovieBasicDTO implements MovieType {
 *     // ... implementation
 * }
 *
 * // Only Movie types allowed
 * List<? extends MovieType> movies = ...;
 * }</pre>
 *
 * @see Movie
 */
public interface MovieType {
}
