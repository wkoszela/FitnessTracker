package pl.wsb.fitnesstracker.achievement;

import jakarta.persistence.*;
import pl.wsb.fitnesstracker.user.api.User;
import java.time.LocalDateTime;

@Entity
@Table(name = "achievement")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "earned_at")
    private LocalDateTime earnedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected Achievement() {
    }

    public Achievement(String name, LocalDateTime earnedAt, User user) {
        this.name = name;
        this.earnedAt = earnedAt;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getEarnedAt() {
        return earnedAt;
    }

    public User getUser() {
        return user;
    }
}