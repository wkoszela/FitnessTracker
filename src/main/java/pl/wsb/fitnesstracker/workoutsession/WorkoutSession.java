package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wsb.fitnesstracker.training.api.Training; // Upewnij się, że ten import jest poprawny dla Twojego projektu

@Entity
@Table(name = "workout_session")
@Getter
@Setter
@NoArgsConstructor
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // To naprawi błąd: "workoutSessionTableHasTrainingForeignKey"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @Column(name = "timestamp")
    private String timestamp; // Uwaga: w przyszłości rozważ zmianę na java.time.LocalDateTime

    private double startLatitude;
    private double startLongitude;
    private double endLatitude;
    private double endLongitude;
    private double altitude;

}