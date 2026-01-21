package com.daniil.swimcoach.model.enums;

import lombok.Getter;

@Getter
public enum DifficultyLevel {
    BEGINNER(1, "Beginner", "For swimmers just starting out"),

    INTERMEDIATE(2, "Intermediate", "For swimmers with basic experience"),

    ADVANCED(3, "Advanced", "For experienced swimmers"),

    ELITE(4, "Elite", "For competitive swimmers and athletes");

    private final int level;
    private final String displayName;
    private final String description;

    DifficultyLevel(int level, String displayName, String description) {
        this.level = level;
        this.displayName = displayName;
        this.description = description;
    }

    public static DifficultyLevel fromLevel(int level) {
        for (DifficultyLevel difficulty : values()) {
            if (difficulty.level == level) {
                return difficulty;
            }
        }
        throw new IllegalArgumentException("Invalid difficulty level: " + level);
    }
}
