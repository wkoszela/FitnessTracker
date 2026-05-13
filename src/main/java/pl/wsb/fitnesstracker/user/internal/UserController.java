package pl.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    private final UserProvider userProvider;

    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {

        // TODO: Implement the method to add a new user.
        //  You can use the @RequestBody annotation to map the request body to the UserDto object.

        User user = new User(userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email());
        User newUser = this.userService.createUser(user);

        return this.userMapper.toUserDto(newUser);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED) // sprawdzić co w przypadku wysypania takiej metody
    public UserDto updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto)
    {
        User user = this.userProvider.getUser(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.update(userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email());

        User userUpdated = this.userService.updateUser(user);

        return this.userMapper.toUserDto(userUpdated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id)
    {
        userService.deleteUserById(id);
    }

    @GetMapping
    public List<UserDto> getUsers() throws InterruptedException {

       return this.userProvider.findAllUsers().stream()
                .map(this.userMapper::toUserDto)
                .toList();
    }
    @GetMapping("/simple")
    public List<UserSimpleDto> getSimpleUsers() throws InterruptedException {

        return this.userProvider.findAllUsers().stream()
                .map(this.userMapper::toUserSimpleDto)
                .toList();
    }
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) throws InterruptedException {
        return this.userProvider.getUser(id).stream()
                .map(this.userMapper::toUserDto)
                .toList().get(0);
    }

    @GetMapping("/email")
    public List<UserDto> getUserByEmail(@RequestParam("email") String email)
    {
        return this.userProvider.getUserByEmail(email).stream()
                .map(this.userMapper::toUserDto)
                .toList();
    }

    @GetMapping("/older/{date}")
    @ResponseStatus(HttpStatus.OK)
    public  List<UserDto> getAllUsersOlderThan(@PathVariable("date") LocalDate date)
    {
        List<User> userList = userProvider.findAllUsers().stream()
                .filter(user -> user.getBirthdate().isBefore(date)).toList();

        return userList.stream().map(userMapper::toUserDto).toList();
    }
}