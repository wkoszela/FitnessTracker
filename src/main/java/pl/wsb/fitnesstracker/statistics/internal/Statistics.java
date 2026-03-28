package pl.wsb.fitnesstracker.statistics.internal;

// KLUCZOWY IMPORT: Importujemy Usera z jego własnego modułu
import pl.wsb.fitnesstracker.user.internal.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Statistics")
@Getter
@NoArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacja OneToOne do klasy User z innego pakietu
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    private int totalTrainings;
    private double totalDistance;
    private double totalCaloriesBurned;

    public Statistics(final User user, final int totalTrainings, final double totalDistance, final double totalCaloriesBurned) {
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}