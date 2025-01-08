package org.unito.postgreserver.oscar.model;

/**
 * Marker interface for oscar types.
 * <p>
 * This interface is used to provide type safety for return types of `Oscar` objects (Entity and DTOs).
 * Implementing this interface ensures that only valid oscar types can be used.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * public class OscarBasicDTO implements OscarType {
 *     // ... implementation
 * }
 *
 * // Only Oscar types allowed
 * List<? extends OscarType> oscars = ...;
 * }</pre>
 *
 * @see Oscar
 */
public interface OscarType {
}
