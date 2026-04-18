package pl.wsb.fitnesstracker.statistics.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

@Entity
@Table(name = "statistics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="total_trainings",nullable = false, unique = false)
    private int totalTrainings;

    @Column(name="total_distance",nullable = false, unique = false)
    private double totalDistance;

    @Column(name="total_calories_burned",nullable = false, unique = false)
    private int totalCaloriesBurned;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public Statistics(int totalTrainings, double totalDistance, int totalCaloriesBurned) {
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}
