package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.Getter;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDate;
import java.time.LocalDateTime;

// TODO: Define the Event entity with appropriate fields and annotations
@Entity
@Table(name = "workout_session")
@Getter
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "start_latitude", nullable = false)
    private double startLatitude;

    @Column(name = "start_longitude", nullable = false)
    private double startLongitude;

    @Column(name = "end_latitude", nullable = false)
    private double endLatitude;

    @Column(name = "end_longitude", nullable = false)
    private double endLongitude;

    @Column(name = "altitude", nullable = false)
    private double altitude;

    public WorkoutSession(Long id, Training training, LocalDateTime timestamp, double startLatitude, double startLongitude, double endLatitude,
                          double endLongitude, double altitude){
        this.id = id;
        this.training = training;
        this.timestamp = timestamp;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.altitude = altitude;
    }
}
