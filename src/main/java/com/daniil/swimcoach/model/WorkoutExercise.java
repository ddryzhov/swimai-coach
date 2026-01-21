package com.daniil.swimcoach.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "workout")
@Table(name = "workout_exercises")
public class WorkoutExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @NotBlank
    @Size(max = 200)
    @Column(name = "exercise_name", nullable = false, length = 200)
    private String exerciseName;

    @Min(1)
    @Column(name = "sets")
    private Integer sets;

    @Min(1)
    @Column(name = "reps")
    private Integer reps;

    @Min(0)
    @Column(name = "distance_meters")
    private Integer distanceMeters;

    @Min(0)
    @Column(name = "rest_seconds")
    private Integer restSeconds;

    @Size(max = 100)
    @Column(name = "intensity", length = 100)
    private String intensity;

    @Size(max = 500)
    @Column(name = "notes", length = 500)
    private String notes;

    @NotNull
    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    public int getTotalDistance() {
        if (sets == null || distanceMeters == null) {
            return 0;
        }
        return sets * distanceMeters;
    }
}
