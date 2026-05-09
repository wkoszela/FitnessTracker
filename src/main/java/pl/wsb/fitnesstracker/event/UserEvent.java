package pl.wsb.fitnesstracker.userevent;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.event.Event;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_events")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    public UserEvent(
            final User user,
            final Event event,
            final LocalDateTime registrationDate) {
        this.user = user;
        this.event = event;
        this.registrationDate = registrationDate;
    }
}