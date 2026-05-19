package com.example.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/*
 * Producer concept:
 * A Kafka producer sends records to a topic. Spring's KafkaTemplate hides the
 * lower-level producer API and lets application code focus on the topic, key,
 * and message payload.
 */
@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, KafkaPracticeEvent> kafkaTemplate;
    private final String topicName;

    public KafkaProducerService(
            KafkaTemplate<String, KafkaPracticeEvent> kafkaTemplate,
            @Value("${kafka.topic.practice-events}") String topicName
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public KafkaPracticeEvent send(String message, String sender) {
        KafkaPracticeEvent event = KafkaPracticeEvent.create(message, sender);

        /*
         * The key controls partition choice. Messages with the same key go to the
         * same partition, which preserves their order within that partition.
         */
        kafkaTemplate.send(topicName, event.sender(), event);
        return event;
    }
}
