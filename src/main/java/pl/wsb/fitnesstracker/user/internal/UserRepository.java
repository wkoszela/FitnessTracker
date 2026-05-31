package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */

    // Wyszukuje użytkownika po dokładnym adresie email.
    Optional<User> findByEmailIgnoreCase(String email);

    // Wyszukuje użytkowników po fragmencie imienia lub nazwiska.
    List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

    // Pobiera wszystkich użytkowników jako strumień.
    @Query("select u from User u")
    Stream<User> streamAllUsers();

    // wyszukiwanie po fragmencie email .
    List<User> findByEmailContainingIgnoreCase(String emailFragment);

    Optional<User> findByEmail(String email);
}