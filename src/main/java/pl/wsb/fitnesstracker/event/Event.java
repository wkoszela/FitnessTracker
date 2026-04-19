package pl.wsb.fitnesstracker.event;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.userevent.UserEvent;

import java.awt.desktop.UserSessionEvent;
import java.time.LocalDateTime;

// TODO: Define the Event entity with appropriate fields and annotations
@Entity
@Table(name = "event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String country;
    private String city;
    @ManyToOne
    @JoinColumn(name = "user_event_id", nullable = false)
    private UserEvent userEvent;







}
