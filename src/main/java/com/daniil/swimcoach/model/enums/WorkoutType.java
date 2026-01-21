package com.daniil.swimcoach.model.enums;

import lombok.Getter;

@Getter
public enum WorkoutType {
    TECHNIQUE("Technique", "Focus on swimming form, breathing, and coordination"),

    ENDURANCE("Endurance", "Long-distance swimming for aerobic fitness"),

    SPEED("Speed", "Sprint intervals and anaerobic training"),

    RECOVERY("Recovery", "Low-intensity swimming for muscle recovery"),

    MIXED("Mixed", "Combination of different training focuses");

    private final String displayName;
    private final String description;

    WorkoutType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
