package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.SimpleUserDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;

import java.time.LocalDate;

@Component
class UserMapper {

    UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthdate(), user.getEmail());
    }

    User toUser(UserDto dto) {
        return new User(dto.firstName(), dto.lastName(), dto.birthdate(), dto.email());
    }

    SimpleUserDto toSimpleUserDto(User user) {
        return new SimpleUserDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    User toSimpleUser(SimpleUserDto dto) {
        return new User(dto.firstName(), dto.lastName(), LocalDate.now(), "");
    }

    UserEmailDto toUserEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }
}
