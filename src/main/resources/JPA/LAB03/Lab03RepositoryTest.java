package pl.wsb.fitnesstracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import pl.wsb.fitnesstracker.event.Event;
import pl.wsb.fitnesstracker.event.EventRepository;
import pl.wsb.fitnesstracker.event.UserEvent;
import pl.wsb.fitnesstracker.event.UserEventRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class Lab03RepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserEventRepository userEventRepository;

    /**
     * Weryfikuje zapytanie JPQL z sekcji 3 LAB03:
     *     @Query("SELECT e FROM Event e WHERE e.startDate > :now ORDER BY e.startDate")
     *     List<Event> findUpcoming(@Param("now") LocalDate now);
     */
    @Test
    void findUpcomingReturnsOnlyFutureEvents() {
        Event past = new Event("Past Event", LocalDate.now().minusDays(1), "Warsaw");
        Event future = new Event("Future Event", LocalDate.now().plusDays(1), "Krakow");
        em.persist(past);
        em.persist(future);
        em.flush();

        List<Event> result = eventRepository.findUpcoming(LocalDate.now());

        assertThat(result).extracting(Event::getName).containsExactly("Future Event");
    }

    /**
     * Weryfikuje zapytanie w natywnym SQL z sekcji 4 LAB03:
     *     @Query(value = "SELECT COUNT(*) FROM user_event WHERE event_id = :eventId",
     *            nativeQuery = true)
     *     long countParticipants(@Param("eventId") Long eventId);
     */
    @Test
    void countParticipantsReturnsRegistrationCount() {
        User u1 = new User("John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@test.com");
        User u2 = new User("Jane", "Doe", LocalDate.of(1992, 2, 2), "jane.doe@test.com");
        Event event = new Event("Marathon", LocalDate.now().plusDays(7), "Gdansk");
        em.persist(u1);
        em.persist(u2);
        em.persist(event);
        em.persist(new UserEvent(u1, event));
        em.persist(new UserEvent(u2, event));
        em.flush();

        long count = userEventRepository.countParticipants(event.getId());

        assertThat(count).isEqualTo(2L);
    }
}
