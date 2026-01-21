package com.daniil.swimcoach.repository;

import com.daniil.swimcoach.model.WorkoutExercise;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
    List<WorkoutExercise> findByWorkoutIdOrderByOrderIndexAsc(Long workoutId);

    void deleteByWorkoutId(Long workoutId);

    @Query("SELECT COALESCE(SUM(e.sets * e.distanceMeters), 0) "
            + "FROM WorkoutExercise e WHERE e.workout.id = :workoutId")
    int calculateTotalDistance(@Param("workoutId") Long workoutId);
}
