package pl.wsb.fitnesstracker.statistics.api;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.user.api.User;

@Entity
@Table(name = "statistics")
@Getter
@NoArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "total_trainings", nullable = false)
    private int totalTrainings;

    @Column(name = "total_distance", nullable = true)
    private double totalDistance;

    @Column(name = "total_calories_burned", nullable = true)
    private double totalCaloriesBurned;

    public Statistics(
            final User user,
            final int totalTrainings,
            final double totalDistance,
            final double totalCaloriesBurned) {
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}