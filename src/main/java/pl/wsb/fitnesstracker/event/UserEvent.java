package pl.wsb.fitnesstracker.event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;

@Entity
@Table(name = "user_event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="event_id", nullable = false)
    private Event event;

    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;
}
