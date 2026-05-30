package pl.wsb.fitnesstracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserRepository;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldReturnUsersMatchingDomain() {
        userRepository.save(new User("Alice", "Smith", LocalDate.of(1990, 1, 1), "alice@gmail.com"));
        userRepository.save(new User("Bob", "Jones", LocalDate.of(1985, 5, 20), "bob@gmail.com"));
        userRepository.save(new User("Carol", "Brown", LocalDate.of(1995, 3, 15), "carol@yahoo.com"));

        List<User> result = userRepository.findByEmailDomain("gmail.com");

        assertThat(result).hasSize(2);
        assertThat(result).extracting(User::getEmail)
                .containsExactlyInAnyOrder("alice@gmail.com", "bob@gmail.com");
    }

    @Test
    void shouldReturnEmptyListWhenNobodyMatchesDomain() {
        userRepository.save(new User("Dave", "White", LocalDate.of(1992, 7, 10), "dave@outlook.com"));

        List<User> result = userRepository.findByEmailDomain("gmail.com");

        assertThat(result).isEmpty();
    }

    @Test
    void shouldNotMatchPartialDomain() {
        userRepository.save(new User("Eve", "Black", LocalDate.of(1988, 11, 3), "eve@notgmail.com"));

        List<User> result = userRepository.findByEmailDomain("gmail.com");

        assertThat(result).isEmpty();
    }
}
