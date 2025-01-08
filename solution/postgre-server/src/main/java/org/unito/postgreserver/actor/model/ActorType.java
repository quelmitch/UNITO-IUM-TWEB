package org.unito.postgreserver.actor.model;

/**
 * Marker interface for actor types.
 * <p>
 * This interface is used to provide type safety for return types of `Actor` objects (Entity and DTOs).
 * Implementing this interface ensures that only valid actor types can be used.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * public class ActorBasicDTO implements ActorType {
 *     // ... implementation
 * }
 *
 * // Only Actor types allowed
 * List<? extends ActorType> actors = ...;
 * }</pre>
 *
 * @see Actor
 */
public interface ActorType {
}
