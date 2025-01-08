package org.unito.postgreserver.genre.model;

/**
 * Marker interface for genre types.
 * <p>
 * This interface is used to provide type safety for return types of `Genre` objects (Entity and DTOs).
 * Implementing this interface ensures that only valid genre types can be used.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * public class Genre implements GenreType {
 *     // ... implementation
 * }
 *
 * // Only Genre types allowed
 * List<? extends GenreType> genres = ...;
 * }</pre>
 *
 * @see Genre
 */
public interface GenreType {
}
