package pl.wsb.fitnesstracker.event;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

// TODO: Define the Event entity with appropriate fields and annotations
@Entity
@Table(name = "event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long Id;

    @Column(name="name", nullable = false)
    private String Name;

    @Column(name="start_date", nullable = false)
    private Date StartDate;

    @Column(name="location", nullable = false)
    private String location;

}
