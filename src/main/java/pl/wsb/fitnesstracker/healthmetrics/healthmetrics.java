package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "Health_Metrics")
public class healthmetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /// zmienic na klucz obcy
    // @ManyToOne
    @JoinColumn(name = "user_id")
    private int user_id;


    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "weight", nullable = false)
    private float weight;

    @Column(name = "height", nullable = false)
    private int height; //cm

    @Column(name = "heartRate", nullable = false)
    private int heartRate; //bpm

}
