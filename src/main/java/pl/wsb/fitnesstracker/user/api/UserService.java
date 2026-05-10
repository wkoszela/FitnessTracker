package pl.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user the user to be created
     * @return the created user with assigned ID
     */
    User createUser(User user);

    /**
     * Updates an existing user identified by {@code userId} with data from {@code user}.
     *
     * @param userId the ID of the user to update
     * @param user   the user data to apply
     * @return the updated user
     */
    User updateUser(Long userId, User user);

    /**
     * Deletes the user with the given {@code userId}.
     *
     * @param userId the ID of the user to delete
     */
    void deleteUser(Long userId);

}
