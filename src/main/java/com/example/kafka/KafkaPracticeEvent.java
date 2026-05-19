package com.example.kafka;

import java.time.Instant;

/*
 * This record is the message payload that moves through Kafka.
 *
 * Kafka stores bytes, not Java objects. Spring Kafka uses JsonSerializer and
 * JsonDeserializer from application.yml to convert this record to JSON when
 * producing and back to a Java object when consuming.
 */
public record KafkaPracticeEvent(
        String id,
        String message,
        String sender,
        Instant createdAt
) {
    public static KafkaPracticeEvent create(String message, String sender) {
        return new KafkaPracticeEvent(
                java.util.UUID.randomUUID().toString(),
                message,
                sender,
                Instant.now()
        );
    }
}
