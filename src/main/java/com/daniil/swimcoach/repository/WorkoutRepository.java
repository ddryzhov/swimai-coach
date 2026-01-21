package com.daniil.swimcoach.repository;

import com.daniil.swimcoach.model.Workout;
import com.daniil.swimcoach.model.enums.DifficultyLevel;
import com.daniil.swimcoach.model.enums.WorkoutType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    Page<Workout> findByUserId(Long userId, Pageable pageable);

    List<Workout> findByUserIdAndGeneratedAtBetween(
            Long userId,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    Page<Workout> findByUserIdAndType(Long userId, WorkoutType type, Pageable pageable);

    Page<Workout> findByUserIdAndDifficulty(
            Long userId,
            DifficultyLevel difficulty,
            Pageable pageable
    );

    @Query("SELECT w FROM Workout w LEFT JOIN FETCH w.exercises WHERE w.id = :id")
    Optional<Workout> findByIdWithExercises(@Param("id") Long id);

    @Query("SELECT w FROM Workout w WHERE w.user.id = :userId "
            + "ORDER BY w.generatedAt DESC LIMIT 1")
    Optional<Workout> findLatestByUserId(@Param("userId") Long userId);

    long countByUserId(Long userId);

    long countByUserIdAndCompleted(Long userId, boolean completed);

    Page<Workout> findByUserIdAndCompleted(Long userId, boolean completed, Pageable pageable);
}
