package pl.wsb.fitnesstracker.achievement;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "achievements")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "earned_at", nullable = false)
    private LocalDateTime earnedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Achievement(
            final String name,
            final LocalDateTime earnedAt,
            final User user) {
        this.name = name;
        this.earnedAt = earnedAt;
        this.user = user;
    }
}