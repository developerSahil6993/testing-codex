package com.example.kafka;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/*
 * REST controller for manual practice.
 *
 * Send an HTTP POST request to /api/kafka/messages. The controller calls the
 * producer service, the producer writes to Kafka, and the consumer logs the
 * same event when it reads the topic.
 */
@RestController
@RequestMapping("/api/kafka")
public class KafkaPracticeController {
    private final KafkaProducerService producerService;

    public KafkaPracticeController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/messages")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public KafkaPracticeEvent publish(@RequestBody PublishKafkaMessageRequest request) {
        return producerService.send(request.message(), request.sender());
    }
}
