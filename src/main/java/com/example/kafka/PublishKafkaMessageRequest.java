package com.example.kafka;

/*
 * Request body for the practice endpoint.
 *
 * Example JSON:
 * {
 *   "message": "Hello Kafka",
 *   "sender": "Sahil"
 * }
 */
public record PublishKafkaMessageRequest(String message, String sender) {
}
