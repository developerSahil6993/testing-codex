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
        Student8 student = new Student8(101, "Anika", "Java 8 comparison", LocalDate.of(2026, 5, 19));

        String report = "Student Practice Card\n"
                + "---------------------\n"
                + "Id: " + student.getId() + "\n"
                + "Name: " + student.getName() + "\n"
                + "Topic: " + student.getTopic() + "\n"
                + "Enrolled: " + student.getEnrolledOn() + "\n";

        System.out.println(report);
    }

    private static void instanceofChains() {
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
        List<String> topics = Arrays.asList("records", "sealed classes", "virtual threads");

        System.out.println("First topic: " + topics.get(0));
        System.out.println("Last topic: " + topics.get(topics.size() - 1));

        Collections.reverse(topics);
        System.out.println("Reverse practice order: " + topics);
    }

    private static void fixedThreadPool() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            for (int index = 1; index <= 3; index++) {
                final int taskId = index;
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
            Thread.sleep(100);
            System.out.printf("%s completed task %d%n", Thread.currentThread().getName(), taskId);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Worker interrupted", exception);
        }
    }
}
