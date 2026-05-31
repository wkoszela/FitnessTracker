package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

@Component
class UserSimpleMapper {
    UserSimpleDto toUserSimpleDto(User user) {
        return new UserSimpleDto(user.getId(), user.getFirstName(), user.getLastName());
    }
}
