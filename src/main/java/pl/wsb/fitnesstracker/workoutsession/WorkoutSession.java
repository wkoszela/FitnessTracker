package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workout_session")
@NoArgsConstructor
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long trainingId;
    private String timestamp;
    private double startLatitude;
    private double startLongitude;
    private double endLatitude;
    private double endLongitude;
    private double altitude;
}
