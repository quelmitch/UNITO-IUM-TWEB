package org.unito.postgreserver.theme.model;

/**
 * Marker interface for theme types.
 * <p>
 * This interface is used to provide type safety for return types of `Theme` objects (Entity and DTOs).
 * Implementing this interface ensures that only valid theme types can be used.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * public class Theme implements ThemeType {
 *     // ... implementation
 * }
 *
 * // Only Theme types allowed
 * List<? extends ThemeType> themes = ...;
 * }</pre>
 *
 * @see Theme
 */
public interface ThemeType {
}
