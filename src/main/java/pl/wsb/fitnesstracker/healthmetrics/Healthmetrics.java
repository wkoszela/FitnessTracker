package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@Entity
@Table(name = "health_metrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Healthmetrics {

    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Nullable
        private Long id;

        @Column(name = "user_id", nullable = false)
        private long userId;

        @Column(name = "date", nullable = false)
        private LocalDate date;

        @Column(name = "weight", nullable = false)
        private int weight;

        @Column(name = "height", nullable = false)
        private long height;

        @Column(name = "heart_rate", nullable = false)
        private int heartRate;

    public Healthmetrics(long userId, LocalDate date, int weight, long height, int heartRate, @Nullable Long id) {
        this.userId = userId;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
        this.id = id;
    }


}
