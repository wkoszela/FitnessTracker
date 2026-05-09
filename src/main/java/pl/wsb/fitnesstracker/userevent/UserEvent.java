package pl.wsb.fitnesstracker.userevent;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;



@Entity
@Table(name = "userevent")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private User user2;

    @Column(nullable = false)
    private String status;


    public UserEvent(User user, User user2, String status) {

        this.user = user;
        this.user2 = user2;
        this.status = status;

    }
}




