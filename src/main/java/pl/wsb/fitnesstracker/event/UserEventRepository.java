package pl.wsb.fitnesstracker.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
}
