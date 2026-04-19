package pl.wsb.fitnesstracker.statistics.api;

import jakarta.persistence.*;
import lombok.*;
import pl.wsb.fitnesstracker.user.api.User;

@Entity
@Table(name = "statistics")
@Getter
@Setter // Dodane, aby serwis mógł aktualizować statystyki po treningu
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "total_trainings", nullable = false)
    private int totalTrainings;

    @Column(name = "total_distance", nullable = false)
    private double totalDistance;

    @Column(name = "total_calories_burned", nullable = false)
    private long totalCaloriesBurned; // Zmieniono na long, jeśli przewidujemy duże liczby

    public Statistics(User user) { // Uproszczony konstruktor - nowe statystyki startują od 0
        this.user = user;
        this.totalTrainings = 0;
        this.totalDistance = 0.0;
        this.totalCaloriesBurned = 0;
    }

    public Statistics(User user, int totalTrainings, double totalDistance, long totalCaloriesBurned) {
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}