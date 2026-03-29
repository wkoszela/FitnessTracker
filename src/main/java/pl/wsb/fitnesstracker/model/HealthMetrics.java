package pl.wsb.fitnesstracker.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "health_metrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "weight")
    private int weight;

    @Column(name = "height")
    private int height;

    @Column(name = "heart_rate")
    private int heartRate;


    public HealthMetrics(int userId, LocalDate date, int weight, int height, int heartRate) {
        this.userId = userId;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
    }
}