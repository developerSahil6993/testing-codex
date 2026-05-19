# testing-codex

A small Java starter project created with Codex.

## Requirements

- JDK 21 or newer

## Build and Run

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
javac -d build/classes $(find src/main/java -name "*.java")
```

Run the multithreading practice runner:

```sh
java -cp build/classes com.example.multithreading.MultithreadingPractice
```

The package includes examples for:

- Creating and joining threads
- Race conditions
- Synchronized methods
- ExecutorService with Callable and Future
- Producer-consumer using BlockingQueue
