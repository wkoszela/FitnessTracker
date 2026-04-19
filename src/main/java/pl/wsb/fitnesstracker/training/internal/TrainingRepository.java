package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query(
            value = "SELECT COALESCE(SUM(distance), 0) FROM trainings WHERE user_id = :userId",
            nativeQuery = true
    )
    double sumDistanceByUserId(@Param("userId") Long userId);
}