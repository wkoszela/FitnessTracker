package pl.wsb.fitnesstracker.user.api;

/**
 * DTO containing only identification and email info for a user.
 */
public record UserEmailDto(Long id, String email) {
}
