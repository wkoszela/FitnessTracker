package pl.wsb.fitnesstracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.wsb.fitnesstracker.event.Event;
import pl.wsb.fitnesstracker.event.EventRepository;
import pl.wsb.fitnesstracker.event.EventRepositoryImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class EventRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void shouldHaveEventTable() {
        EventRepositoryImpl tego = new EventRepositoryImpl();
        List<Event> events = tego.findUpcoming(LocalDate.now());
        System.out.println("Nadchodzące eventy: " + events.size());
        assertThat(events).isNotEmpty();
    }
}
