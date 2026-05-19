# testing-codex

A small Java starter project created with Codex.

## Requirements

- JDK 21 or newer

## Build and Run With Maven

Compile the full project, including Spring Boot Kafka classes:

```sh
mvn compile
```

Run the Spring Boot Kafka demo:

```sh
mvn spring-boot:run
```

## Basic Java App

Compile the project:

```sh
javac -d build/classes src/main/java/com/example/App.java
```

Run the application:

```sh
java -cp build/classes com.example.App
```

## Multithreading Practice

Compile all examples:

```sh
mvn compile
```

Run the multithreading practice runner:

```sh
java -cp target/classes com.example.multithreading.MultithreadingPractice
```

The package includes examples for:

- Creating and joining threads
- Race conditions
- Synchronized methods
- ExecutorService with Callable and Future
- Producer-consumer using BlockingQueue

## Java 21 Practice

Compile all examples:

```sh
mvn compile
```

Run Java 21 examples:

```sh
java -cp target/classes com.example.java21concepts.Java21Practice
```

Run Java 8 comparison examples:

```sh
java -cp target/classes com.example.java8comparison.Java8ComparisonPractice
```

Read the detailed guide:

```sh
src/main/java/com/example/java21concepts/JAVA_21_VS_JAVA_8.md
```

## Kafka Practice

Start Kafka locally:

```sh
docker compose up -d
```

Run the Spring Boot app:

```sh
mvn spring-boot:run
```

Publish a Kafka message through the REST API:

```sh
curl -X POST http://localhost:8080/api/kafka/messages \
  -H "Content-Type: application/json" \
  -d '{"message":"Hello Kafka from Spring Boot","sender":"Sahil"}'
```

Read the detailed guide:

```sh
src/main/java/com/example/kafka/KAFKA_PRACTICE.md
```
