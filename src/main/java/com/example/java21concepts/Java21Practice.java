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
        Student student = new Student(101, "Anika", "Java 21", LocalDate.of(2026, 5, 19));

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
        return switch (payment) {
            case CardPayment card when card.amount() >= 1_000 ->
                    "High value card payment from " + card.maskedCardNumber();
            case CardPayment card -> "Card payment from " + card.maskedCardNumber();
            case UpiPayment upi -> "UPI payment from " + upi.upiId();
            case CashPayment cash -> "Cash payment of " + cash.amount();
        };
    }

    private static void sequencedCollections() {
        SequencedCollection<String> topics = new ArrayList<>();
        topics.addFirst("records");
        topics.addLast("sealed classes");
        topics.addLast("virtual threads");

        System.out.println("First topic: " + topics.getFirst());
        System.out.println("Last topic: " + topics.getLast());
        System.out.println("Reverse practice order: " + topics.reversed());
    }

    private static void virtualThreads() throws InterruptedException {
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
            Thread.sleep(100);
            System.out.printf("%s completed task %d%n", Thread.currentThread(), taskId);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Virtual thread interrupted", exception);
        }
    }
}
