package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDate;
import java.time.LocalDateTime;

// TODO: Define the Event entity with appropriate fields and annotations

@Entity
@Table(name = "workout_session")
@Getter
@NoArgsConstructor
public class WorkoutSession {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @Column(nullable = false)
    private LocalDateTime timestamp;
    @Column(nullable = false)
    private double startLatitude;
    @Column(nullable = false)
    private double startLongitude;
    @Column(nullable = false)
    private double endLatitude;
    @Column(nullable = false)
    private double endLongitude;
    @Column(nullable = false)
    private double altitude;

    public WorkoutSession(Training training, LocalDateTime timestamp, double startLatitude, double endLatitude,
                          double startLongitude, double endLongitude, double altitude) {
        this.training = training;
        this.timestamp = timestamp;
        this.startLatitude = startLatitude;
        this.endLatitude = endLatitude;
        this.startLongitude = startLongitude;
        this.endLongitude = endLongitude;
        this.altitude = altitude;
    }
}
