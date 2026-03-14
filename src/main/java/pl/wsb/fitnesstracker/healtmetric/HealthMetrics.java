package pl.wsb.fitnesstracker.healtmetric;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "Health_Metrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "weight", nullable = false)
    private Long weight;

    @Column(name = "height", nullable = false)
    private Long height;

    @Column(name = "heartRate", nullable = false)
    private Long heartRate;

    public HealthMetrics(
            final Long user_id,
            final LocalDate date,
            final Long weight,
            final Long height,
            final Long heartRate) {

        this.user_id = user_id;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.height = heartRate;
    }
}