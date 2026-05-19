package com.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/*
 * Consumer concept:
 * A Kafka consumer subscribes to a topic and reads records. Consumers belong to
 * a consumer group. Within one group, Kafka shares partitions across consumers
 * so each record is processed by one consumer in that group.
 */
@Service
public class KafkaConsumerService {
    @KafkaListener(
            topics = "${kafka.topic.practice-events}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void listen(KafkaPracticeEvent event) {
        System.out.printf(
                "Consumed Kafka event: id=%s sender=%s message=%s createdAt=%s%n",
                event.id(),
                event.sender(),
                event.message(),
                event.createdAt()
        );
    }
}
