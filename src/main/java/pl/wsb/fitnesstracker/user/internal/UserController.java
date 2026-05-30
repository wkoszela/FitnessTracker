package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.wsb.fitnesstracker.user.api.*;

import java.util.List;

/**
 * REST controller exposing user management endpoints under {@code /v1/users}.
 *
 * <p>All responses are serialized as JSON. Dates follow the {@code yyyy-MM-dd} format.</p>
 *
 * <ul>
 *   <li>{@code GET    /v1/users}                  – list all users</li>
 *   <li>{@code GET    /v1/users/{id}}              – get a single user</li>
 *   <li>{@code GET    /v1/users/search?email=}     – search users by email (case-insensitive)</li>
 *   <li>{@code GET    /v1/users/older-than/{age}}  – list users older than a given age</li>
 *   <li>{@code POST   /v1/users}                   – create a new user</li>
 *   <li>{@code PUT    /v1/users/{id}}              – update an existing user</li>
 *   <li>{@code DELETE /v1/users/{id}}              – delete a user</li>
 * </ul>
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    private final UserProvider userProvider;

    private final UserMapper userMapper;

    /**
     * Creates a new user.
     *
     * <p><b>POST /v1/users</b></p>
     *
     * @param userDto the user data to create; {@code id} field is ignored
     * @return the created user including the generated {@code id}
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        User created = userService.createUser(userMapper.toUser(userDto));
        return userMapper.toUserDto(created);
    }

    /**
     * Updates an existing user.
     *
     * <p><b>PUT /v1/users/{id}</b></p>
     *
     * <p>All fields are replaced with the values provided in the request body.</p>
     *
     * @param id      the ID of the user to update
     * @param userDto the new user data
     * @return the updated user
     * @throws pl.wsb.fitnesstracker.user.api.UserNotFoundException if no user with the given ID exists
     */
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User updated = userService.updateUser(id, userMapper.toUser(userDto));
        return userMapper.toUserDto(updated);
    }

    /**
     * Deletes a user by ID.
     *
     * <p><b>DELETE /v1/users/{id}</b></p>
     *
     * @param id the ID of the user to delete
     * @throws pl.wsb.fitnesstracker.user.api.UserNotFoundException if no user with the given ID exists
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    /**
     * Retrieves a single user by ID.
     *
     * <p><b>GET /v1/users/{id}</b></p>
     *
     * @param id the ID of the user to retrieve
     * @return the user matching the given ID
     * @throws pl.wsb.fitnesstracker.user.api.UserNotFoundException if no user with the given ID exists
     */
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userProvider.getUser(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Lists all users whose age is strictly greater than the specified value.
     *
     * <p><b>GET /v1/users/older-than/{age}</b></p>
     *
     * @param age the minimum age (exclusive) in years
     * @return list of users older than {@code age}; empty list if none match
     */
    @GetMapping("/older-than/{age}")
    public List<UserDto> getUsersOlderThan(@PathVariable int age) {
        return userProvider.findUsersOlderThan(age).stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    /**
     * Searches users by email address using a case-insensitive partial match.
     *
     * <p><b>GET /v1/users/search?email={email}</b></p>
     *
     * <p>For example, searching for {@code john} will match {@code John@example.com}
     * and {@code JOHN@test.org}. Returns only {@code id} and {@code email} fields.</p>
     *
     * @param email the email fragment to search for
     * @return list of matching users (id and email only); empty list if none match
     */
    @GetMapping("/search")
    public List<UserEmailDto> searchByEmail(@RequestParam String email) {
        return userProvider.searchByEmail(email).stream()
                .map(userMapper::toUserEmailDto)
                .toList();
    }

    /**
     * Retrieves all users.
     *
     * <p><b>GET /v1/users</b></p>
     *
     * @return list of all users; empty list if none exist
     */
    @GetMapping
    public List<SimpleUserDto> getUsers() throws InterruptedException {

       return this.userProvider.findAllUsers().stream()
                .map(this.userMapper::toSimpleUserDto)
                .toList();
    }

}