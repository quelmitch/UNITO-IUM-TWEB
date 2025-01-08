package org.unito.postgreserver.release.model;

/**
 * Marker interface for release types.
 * <p>
 * This interface is used to provide type safety for return types of `Release` objects (Entity and DTOs).
 * Implementing this interface ensures that only valid release types can be used.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * public class ReleasesBasicDTO implements ReleaseType {
 *     // ... implementation
 * }
 *
 * // Only Release types allowed
 * List<? extends ReleaseType> releases = ...;
 * }</pre>
 *
 * @see Release
 */
public interface ReleaseType {
}
