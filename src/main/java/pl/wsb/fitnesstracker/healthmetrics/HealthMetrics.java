package pl.wsb.fitnesstracker.healthmetrics.api;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

@Entity
@Table(name = "health_metrics")
@Getter
@NoArgsConstructor
public class HealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "weight", nullable = true)
    private Double weight;

    @Column(name = "height", nullable = true)
    private Double height;

    @Column(name = "heart_rate", nullable = true)
    private Integer heartRate;

    public HealthMetrics(
            final User user,
            final LocalDate date,
            final Double weight,
            final Double height,
            final Integer heartRate) {
        this.user = user;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
    }
}