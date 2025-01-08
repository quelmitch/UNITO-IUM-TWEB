package org.unito.postgreserver.language.model;

/**
 * Marker interface for Language types.
 * <p>
 * This interface is used to provide type safety for return types of `Language` objects (Entity and DTOs).
 * Implementing this interface ensures that only valid language types can be used.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * public class Language implements LanguageType {
 *     // ... implementation
 * }
 *
 * // Only Language types allowed
 * List<? extends LanguageType> languages = ...;
 * }</pre>
 *
 * @see Language
 */
public interface LanguageType {
}
