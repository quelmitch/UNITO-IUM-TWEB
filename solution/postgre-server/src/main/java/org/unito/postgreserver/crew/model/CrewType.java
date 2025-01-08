package org.unito.postgreserver.crew.model;

/**
 * Marker interface for crew types.
 * <p>
 * This interface is used to provide type safety for return types of `Crew` objects (Entity and DTOs).
 * Implementing this interface ensures that only valid crew types can be used.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * public class CrewBasicDTO implements CrewType {
 *     // ... implementation
 * }
 *
 * // Only Crew types allowed
 * List<? extends CrewType> crewMembers = ...;
 * }</pre>
 *
 * @see Crew
 */
public interface CrewType {
}
