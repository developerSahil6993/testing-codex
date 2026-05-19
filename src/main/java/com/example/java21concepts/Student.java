package com.example.java21concepts;

import java.time.LocalDate;

/*
 * A record is a compact way to define an immutable data carrier.
 *
 * This one line declares:
 * - private final fields for id, name, topic, and enrolledOn
 * - a public constructor
 * - accessor methods named id(), name(), topic(), and enrolledOn()
 * - equals(), hashCode(), and toString()
 */
public record Student(int id, String name, String topic, LocalDate enrolledOn) {
    /*
     * This is a compact constructor. Java runs it as part of the record constructor.
     * It is the right place to validate record data before the object is created.
     */
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
