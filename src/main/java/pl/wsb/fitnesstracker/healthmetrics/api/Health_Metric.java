package pl.wsb.fitnesstracker.healthmetrics.api;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Health_Metrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Health_Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User User;

    @Column(name = "Date", nullable = false)
    private LocalDate Date;

    @Column(name = "Weight", nullable = false)
    private Float Weight;

    @Column(name = "Height", nullable = false)
    private Float Height;

    @Column(name = "Heart_Rate", nullable = false)
    private Long Heart_Rate;

    public Health_Metric(Long Id, User User, LocalDate Date, Float Weight, Float Height, Long Heart_Rate ) {
        this.Id = Id;
        this.User = User;
        this.Date = Date;
        this.Weight = Weight;
        this.Height = Height;
        this.Heart_Rate=Heart_Rate;
    }

}