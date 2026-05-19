# Kafka Practice With Spring Boot

This package demonstrates the basic Kafka flow in a Spring Boot project:

1. An HTTP request enters the Spring Boot application.
2. `KafkaPracticeController` receives the request.
3. `KafkaProducerService` publishes a `KafkaPracticeEvent` to a Kafka topic.
4. Kafka stores the event in the topic.
5. `KafkaConsumerService` listens to the topic and prints the consumed event.

## Dependency Added

The Kafka dependency is declared in `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

The project also uses:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

`spring-boot-starter-web` gives the project REST APIs. `spring-kafka` gives the project Kafka producer, consumer, listener, and serialization support.

## Package Files

| File | Role |
| --- | --- |
| `KafkaDemoApplication.java` | Starts the Spring Boot application. |
| `KafkaPracticeEvent.java` | Message payload sent through Kafka. |
| `KafkaTopicConfig.java` | Creates the Kafka topic for practice. |
| `KafkaProducerService.java` | Sends messages to Kafka. |
| `KafkaConsumerService.java` | Listens to Kafka and consumes messages. |
| `KafkaPracticeController.java` | Exposes an HTTP endpoint for sending Kafka messages. |
| `PublishKafkaMessageRequest.java` | Request body for the HTTP endpoint. |

## Start Kafka

This project includes a local Kafka setup in `docker-compose.yml`.

```sh
docker compose up -d
```

Kafka will be available at:

```text
localhost:9092
```

## Run the Spring Boot App

```sh
mvn spring-boot:run
```

The app starts at:

```text
http://localhost:8080
```

## Publish a Message

In another terminal:

```sh
curl -X POST http://localhost:8080/api/kafka/messages \
  -H "Content-Type: application/json" \
  -d '{"message":"Hello Kafka from Spring Boot","sender":"Sahil"}'
```

Expected behavior:

- The HTTP response returns the event that was sent.
- The Spring Boot logs show `Consumed Kafka event...` after the listener receives it.

## Concept Notes

### Topic

A topic is a named stream of messages. In this project, the topic is:

```text
practice-events
```

### Producer

A producer writes messages to a topic. `KafkaProducerService` uses `KafkaTemplate` to send the event.

### Consumer

A consumer reads messages from a topic. `KafkaConsumerService` uses `@KafkaListener`.

### Consumer Group

A consumer group lets multiple app instances share the work. Each message from a partition is processed by one consumer in the same group.

### Key

The producer sends `sender` as the Kafka key. Kafka uses the key to choose a partition. Messages with the same key stay ordered within that partition.

### Serialization

Kafka sends bytes. This project uses JSON serialization so `KafkaPracticeEvent` can be sent as JSON and received as a Java record.
