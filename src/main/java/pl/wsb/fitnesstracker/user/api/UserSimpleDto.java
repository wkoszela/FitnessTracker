package pl.wsb.fitnesstracker.user.api;

public record UserSimpleDto(
        Long id,
        String firstName,
        String lastName
) {}