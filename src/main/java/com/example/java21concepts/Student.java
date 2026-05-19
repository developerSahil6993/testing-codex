package com.example.java21concepts;

import java.time.LocalDate;

public record Student(int id, String name, String topic, LocalDate enrolledOn) {
    public Student {
        if (id <= 0) {
            throw new IllegalArgumentException("id must be positive");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }

        if (topic == null || topic.isBlank()) {
            throw new IllegalArgumentException("topic is required");
        }
    }
}
