package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

@Entity
@Table(name = "health_metrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @ManyToOne
    private Long user_id;

    private int date;
    private int weight;
    private int height;
    private int heartRate;


    public HealthMetrics(
            final Long user,
            final int date,
            final int weight,
            final int height,
            final int heartRate) {
        this.user_id = user;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
    }

}
