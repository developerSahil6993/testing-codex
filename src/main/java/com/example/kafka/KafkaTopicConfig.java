package com.example.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/*
 * Kafka topics are named streams of records.
 *
 * This configuration creates the practice topic automatically when the app
 * starts, as long as Kafka is reachable and topic auto-creation/admin operations
 * are allowed. For learning projects, this removes a manual setup step.
 */
@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic practiceEventsTopic(@Value("${kafka.topic.practice-events}") String topicName) {
        return TopicBuilder.name(topicName)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
