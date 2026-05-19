package com.example.java21concepts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

public final class Java21Practice {
    private Java21Practice() {
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Records and text blocks ===");
        recordsAndTextBlocks();

        System.out.println();
        System.out.println("=== Pattern matching with sealed types ===");
        patternMatchingWithSealedTypes();

        System.out.println();
        System.out.println("=== Sequenced collections ===");
        sequencedCollections();

        System.out.println();
        System.out.println("=== Virtual threads ===");
        virtualThreads();
    }

    private static void recordsAndTextBlocks() {
        /*
         * Record concept:
         * Student is a record, so Java automatically creates a constructor,
         * accessor methods like id(), equals(), hashCode(), and toString().
         * Use records when the main purpose of a class is to carry immutable data.
         */
        Student student = new Student(101, "Anika", "Java 21", LocalDate.of(2026, 5, 19));

        /*
         * Text block concept:
         * Triple quotes let you write multi-line text in the same shape that it
         * should appear in the output. The formatted(...) call fills placeholders.
         */
        String report = """
                Student Practice Card
                ---------------------
                Id: %d
                Name: %s
                Topic: %s
                Enrolled: %s
                """.formatted(student.id(), student.name(), student.topic(), student.enrolledOn());

        System.out.println(report);
    }

    private static void patternMatchingWithSealedTypes() {
        /*
         * Sealed type concept:
         * Payment is a sealed interface, so only CardPayment, UpiPayment, and
         * CashPayment can implement it. This lets the compiler check whether the
         * switch expression below handles every possible payment type.
         */
        List<Payment> payments = List.of(
                new CardPayment("card-991", 1_250),
                new UpiPayment("student@upi", 650),
                new CashPayment(300)
        );

        for (Payment payment : payments) {
            System.out.println(describe(payment));
        }
    }

    private static String describe(Payment payment) {
        /*
         * Pattern matching switch concept:
         * Each case both checks the type and creates a typed variable. For example,
         * "case UpiPayment upi" means: if payment is UpiPayment, use it as upi.
         * The "when" clause is a guard for extra conditions.
         */
        return switch (payment) {
            case CardPayment card when card.amount() >= 1_000 ->
                    "High value card payment from " + card.maskedCardNumber();
            case CardPayment card -> "Card payment from " + card.maskedCardNumber();
            case UpiPayment upi -> "UPI payment from " + upi.upiId();
            case CashPayment cash -> "Cash payment of " + cash.amount();
        };
    }

    private static void sequencedCollections() {
        /*
         * SequencedCollection concept:
         * Java 21 gives ordered collections a common API for first, last, and
         * reversed access. Code no longer needs to know whether the collection is
         * an ArrayList, LinkedList, or another ordered collection.
         */
        SequencedCollection<String> topics = new ArrayList<>();
        topics.addFirst("records");
        topics.addLast("sealed classes");
        topics.addLast("virtual threads");

        System.out.println("First topic: " + topics.getFirst());
        System.out.println("Last topic: " + topics.getLast());
        System.out.println("Reverse practice order: " + topics.reversed());
    }

    private static void virtualThreads() throws InterruptedException {
        /*
         * Virtual thread concept:
         * Virtual threads are lightweight threads managed by the JVM. They are
         * useful when you have many tasks that spend time waiting, such as calls
         * to databases, APIs, files, or other blocking operations.
         */
        List<Thread> workers = new ArrayList<>();

        for (int index = 1; index <= 3; index++) {
            int taskId = index;
            Thread worker = Thread.ofVirtual()
                    .name("virtual-practice-" + taskId)
                    .start(() -> simulateBlockingTask(taskId));
            workers.add(worker);
        }

        for (Thread worker : workers) {
            worker.join();
        }
    }

    private static void simulateBlockingTask(int taskId) {
        try {
            // Sleep is used here to mimic a blocking operation like an API call.
            Thread.sleep(100);
            System.out.printf("%s completed task %d%n", Thread.currentThread(), taskId);
        } catch (InterruptedException exception) {
            // Restore the interrupted flag so higher-level code can react correctly.
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Virtual thread interrupted", exception);
        }
    }
}
