package pl.wsb.fitnesstracker.userevent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface UserEventRepository extends JpaRepository<UserEvent, Long> {

    //Przykład z z instrukcji
    @Query(
            value = "SELECT COUNT(*) FROM user_event WHERE event_id = :eventId",
            nativeQuery = true
    )
    long countParticipants(@Param("eventId") Long eventId);





}
