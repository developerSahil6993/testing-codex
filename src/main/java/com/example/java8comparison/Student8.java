package com.example.java8comparison;

import java.time.LocalDate;
import java.util.Objects;

/*
 * Java 8 style immutable data class.
 *
 * This class represents the same idea as the Java 21 Student record, but every
 * piece has to be written manually: fields, constructor, validation, getters,
 * and toString. This is the boilerplate records were designed to reduce.
 */
public final class Student8 {
    private final int id;
    private final String name;
    private final String topic;
    private final LocalDate enrolledOn;

    public Student8(int id, String name, String topic, LocalDate enrolledOn) {
        // Constructor validation protects the object from invalid state.
        if (id <= 0) {
            throw new IllegalArgumentException("id must be positive");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name is required");
        }

        if (topic == null || topic.trim().isEmpty()) {
            throw new IllegalArgumentException("topic is required");
        }

        this.id = id;
        this.name = name;
        this.topic = topic;
        this.enrolledOn = Objects.requireNonNull(enrolledOn, "enrolledOn is required");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public LocalDate getEnrolledOn() {
        return enrolledOn;
    }

    @Override
    public String toString() {
        // In Java 8, useful object output has to be written manually.
        return "Student8{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", topic='" + topic + '\''
                + ", enrolledOn=" + enrolledOn
                + '}';
    }
}
