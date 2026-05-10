package pl.wsb.fitnesstracker.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {


@Query(
            value = """
        SELECT e.name, COUNT(ue.user_id)
        FROM event e
        JOIN user_event ue ON e.id = ue.event_id
        GROUP BY e.name
    """,
            nativeQuery = true
    )
List<Object[]> findEventParticipants();

    @Query("SELECT e FROM Event e WHERE e.city = :city")
    List<Event> findByCity(@Param("city") String city);

}
