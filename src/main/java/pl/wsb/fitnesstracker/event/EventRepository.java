package pl.wsb.fitnesstracker.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.startTime > :now ORDER BY e.startTime")
    List<Event> findUpcoming(@Param("now") LocalDateTime now);

    @Query(
            value = "SELECT e.name, COUNT(ue.id) FROM event e LEFT JOIN user_event ue ON e.id = ue.event_id GROUP BY e.id, e.name",
            nativeQuery = true
    )

    List<Object[]> getEventsWithParticipantCount();
}
