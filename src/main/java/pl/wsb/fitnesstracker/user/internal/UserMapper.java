package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

/**
 * Maps between {@link User} entities and their corresponding DTO representations.
 */
@Component
class UserMapper {

    /**
     * Maps a {@link User} to a full {@link UserDto}.
     *
     * @param user the user entity
     * @return the full DTO
     */
    UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthdate(), user.getEmail());
    }

    /**
     * Maps a {@link User} to a {@link UserSimpleDto} containing only ID, first name and last name.
     *
     * @param user the user entity
     * @return the simple DTO
     */
    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    /**
     * Maps a {@link User} to a {@link UserEmailDto} containing only ID and email.
     *
     * @param user the user entity
     * @return the email DTO
     */
    UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }

    /**
     * Maps a {@link UserDto} to a {@link User} entity (without ID).
     *
     * @param dto the DTO carrying the user data
     * @return a transient {@link User} entity
     */
    User toUser(UserDto dto) {
        return new User(dto.firstName(), dto.lastName(), dto.birthdate(), dto.email());
    }
}
