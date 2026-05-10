package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * DTO containing only basic identification info about a user (ID, first name, last name).
 */
public record UserSimpleDto(@Nullable Long id, String firstName, String lastName) {
}
