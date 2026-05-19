package com.example.java8comparison;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class Java8ComparisonPractice {
    private Java8ComparisonPractice() {
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Class instead of record, string building instead of text block ===");
        classBasedDataModel();

        System.out.println();
        System.out.println("=== instanceof chains instead of pattern matching switch ===");
        instanceofChains();

        System.out.println();
        System.out.println("=== List utility methods instead of sequenced collections ===");
        listUtilities();

        System.out.println();
        System.out.println("=== Fixed thread pool instead of virtual threads ===");
        fixedThreadPool();
    }

    private static void classBasedDataModel() {
        /*
         * Java 8 style data model:
         * Before records, simple data holders usually needed explicit fields,
         * constructors, getters, validation, and toString/equals/hashCode if needed.
         * Compare this with the Student record in the Java 21 package.
         */
        Student8 student = new Student8(101, "Anika", "Java 8 comparison", LocalDate.of(2026, 5, 19));

        /*
         * Java 8 has no text blocks, so multi-line output is commonly built with
         * string concatenation and explicit newline characters.
         */
        String report = "Student Practice Card\n"
                + "---------------------\n"
                + "Id: " + student.getId() + "\n"
                + "Name: " + student.getName() + "\n"
                + "Topic: " + student.getTopic() + "\n"
                + "Enrolled: " + student.getEnrolledOn() + "\n";

        System.out.println(report);
    }

    private static void instanceofChains() {
        /*
         * Java 8 comparison for sealed types:
         * Payment8 is a normal interface, so any class can implement it. The
         * compiler cannot guarantee that describe(...) handles every payment type.
         */
        List<Payment8> payments = Arrays.asList(
                new CardPayment8("card-991", 1_250),
                new UpiPayment8("student@upi", 650),
                new CashPayment8(300)
        );

        for (Payment8 payment : payments) {
            System.out.println(describe(payment));
        }
    }

    private static String describe(Payment8 payment) {
        /*
         * Java 8 type handling:
         * You check the type with instanceof, then manually cast before accessing
         * subtype-specific methods. Java 21 pattern matching switch removes most
         * of this ceremony.
         */
        if (payment instanceof CardPayment8) {
            CardPayment8 card = (CardPayment8) payment;

            if (card.getAmount() >= 1_000) {
                return "High value card payment from " + card.getMaskedCardNumber();
            }

            return "Card payment from " + card.getMaskedCardNumber();
        }

        if (payment instanceof UpiPayment8) {
            UpiPayment8 upi = (UpiPayment8) payment;
            return "UPI payment from " + upi.getUpiId();
        }

        if (payment instanceof CashPayment8) {
            CashPayment8 cash = (CashPayment8) payment;
            return "Cash payment of " + cash.getAmount();
        }

        throw new IllegalArgumentException("Unknown payment type: " + payment.getClass().getName());
    }

    private static void listUtilities() {
        /*
         * Java 8 collection style:
         * There is no SequencedCollection API, so first/last/reversed operations
         * are written manually with indexes and Collections.reverse(...).
         */
        List<String> topics = Arrays.asList("records", "sealed classes", "virtual threads");

        System.out.println("First topic: " + topics.get(0));
        System.out.println("Last topic: " + topics.get(topics.size() - 1));

        Collections.reverse(topics);
        System.out.println("Reverse practice order: " + topics);
    }

    private static void fixedThreadPool() throws InterruptedException {
        /*
         * Java 8 concurrency style:
         * ExecutorService is still useful, but platform threads are heavier than
         * Java 21 virtual threads. For many blocking tasks, you must think more
         * carefully about pool size, queueing, and resource limits.
         */
        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            for (int index = 1; index <= 3; index++) {
                final int taskId = index;
                /*
                 * Anonymous Runnable classes were common in Java 8-era code.
                 * A lambda could also be used in Java 8, but this verbose form
                 * makes the older style contrast easier to see.
                 */
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        simulateBlockingTask(taskId);
                    }
                });
            }
        } finally {
            executor.shutdown();
        }

        if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }

    private static void simulateBlockingTask(int taskId) {
        try {
            // This sleep simulates blocking work running on a platform thread.
            Thread.sleep(100);
            System.out.printf("%s completed task %d%n", Thread.currentThread().getName(), taskId);
        } catch (InterruptedException exception) {
            // Restore the interrupted flag after catching InterruptedException.
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Worker interrupted", exception);
        }
    }
}
