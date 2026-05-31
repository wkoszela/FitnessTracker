package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.*;

import java.util.List;

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
    private final UserSimpleMapper userSimpleMapper;

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {

        // TODO: Implement the method to add a new user.
        //  You can use the @RequestBody annotation to map the request body to the UserDto object.

        return null;
    }

    @GetMapping
    public List<UserDto> getUsers() throws InterruptedException {

       return this.userProvider.findAllUsers().stream()
                .map(this.userMapper::toUserDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<UserSimpleDto> getSimpleUsers(){

        return this.userProvider.findAllUsers().stream()
                .map(this.userSimpleMapper::toUserSimpleDto)
                .toList();
    }

}