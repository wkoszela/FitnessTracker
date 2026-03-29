package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Entity
@Table(name = "healthMetrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HealthMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column(name = "user_id", nullable = false)
    private int userID;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "height", nullable = false)
    private double height;

    @Column(name = "hearthRate", nullable = false)
    private int hearthRate;

    public HealthMetrics(
            final int userID,
            final LocalDate date,
            final double weight,
            final double height,
            final int hearthRate){
                this.userID = userID;
                this.date = date;
                this.weight = weight;
                this.height = height;
                this.hearthRate = hearthRate;
    }

}
