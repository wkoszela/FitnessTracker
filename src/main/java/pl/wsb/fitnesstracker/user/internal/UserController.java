package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller exposing CRUD operations for {@link User} entities under {@code /v1/users}.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final UserProvider userProvider;
    private final UserMapper userMapper;

    /**
     * Returns full details for all users.
     *
     * @return list of full {@link UserDto}
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    /**
     * Returns basic info (ID, first name, last name) for all users.
     *
     * @return list of {@link UserSimpleDto}
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    /**
     * Returns full details for the user with the given ID.
     *
     * @param id the user ID
     * @return full {@link UserDto}
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userProvider.getUser(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Searches users by e-mail fragment (case-insensitive). Returns only ID and e-mail.
     *
     * @param email the e-mail fragment to search
     * @return list of {@link UserEmailDto}
     */
    @GetMapping("/email")
    public List<UserEmailDto> getUsersByEmail(@RequestParam String email) {
        return userProvider.findUsersByEmail(email).stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    /**
     * Returns all users born before the given date (i.e. older than that date implies).
     *
     * @param time the reference date in {@code yyyy-MM-dd} format
     * @return list of full {@link UserDto}
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate time) {
        return userProvider.findUsersOlderThan(time).stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    /**
     * Creates a new user.
     *
     * @param userDto the user data
     * @return the created user as {@link UserDto}
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        User created = userService.createUser(userMapper.toUser(userDto));
        return userMapper.toUserDto(created);
    }

    /**
     * Updates the user with the given ID.
     *
     * @param userId  the ID of the user to update
     * @param userDto the new user data
     * @return the updated user as {@link UserDto}
     */
    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User updated = userService.updateUser(userId, userMapper.toUser(userDto));
        return userMapper.toUserDto(updated);
    }

    /**
     * Deletes the user with the given ID.
     *
     * @param userId the ID of the user to delete
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}
