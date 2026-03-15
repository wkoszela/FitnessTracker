package pl.wsb.fitnesstracker.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "health_metrics") // Jawna nazwa tabeli
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weight", nullable = false)
    private int weight;

    // Konstruktor dla Ciebie, żebyś mógł łatwo tworzyć obiekty
    public HealthMetrics(int weight) {
        this.weight = weight;
    }
}