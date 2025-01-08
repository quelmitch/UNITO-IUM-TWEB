package org.unito.postgreserver.studio.model;

/**
 * Marker interface for studio types.
 * <p>
 * This interface is used to provide type safety for return types of `Studio` objects (Entity and DTOs).
 * Implementing this interface ensures that only valid studio types can be used.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * public class StudioBasicDTO implements StudioType {
 *     // ... implementation
 * }
 *
 * // Only Studio types allowed
 * List<? extends StudioType> studios = ...;
 * }</pre>
 *
 * @see Studio
 */
public interface StudioType {
}
