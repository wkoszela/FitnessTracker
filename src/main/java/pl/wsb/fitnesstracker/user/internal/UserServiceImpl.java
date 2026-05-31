package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB id, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public List<User> findUsersByName(final String name) {
        return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteUser(final Long userId) {
        log.info("Deleting User with ID {}", userId);
        userRepository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findUsersByEmailFragment(final String emailFragment) {
        // użycie stream() do filtrowania danych
        try (var stream = userRepository.streamAllUsers()) {
            return stream
                    .filter(user -> user.getEmail().toLowerCase().contains(emailFragment.toLowerCase()))
                    .collect(Collectors.toList());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findUsersOlderThan(final LocalDate date) {
        try (var stream = userRepository.streamAllUsers()) {
            return stream
                    .filter(user -> user.getBirthdate() != null && user.getBirthdate().isBefore(date))
                    .collect(Collectors.toList());
        }
    }

    @Transactional
    @Override
    public User updateUser(final Long userId, final User updatedUserFields) {
        log.info("Updating User with ID {}", userId);
        return userRepository.findById(userId)
                .map(user -> {
                    if (updatedUserFields.getFirstName() != null) user.setFirstName(updatedUserFields.getFirstName());
                    if (updatedUserFields.getLastName() != null) user.setLastName(updatedUserFields.getLastName());
                    if (updatedUserFields.getBirthdate() != null) user.setBirthdate(updatedUserFields.getBirthdate());
                    if (updatedUserFields.getEmail() != null) user.setEmail(updatedUserFields.getEmail());
                    return user;
                })
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found"));    }
}