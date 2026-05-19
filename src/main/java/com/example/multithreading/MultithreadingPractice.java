package com.example.multithreading;

public final class MultithreadingPractice {
    private MultithreadingPractice() {
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Thread basics ===");
        ThreadBasics.run();

        System.out.println();
        System.out.println("=== Race condition ===");
        RaceConditionDemo.run();

        System.out.println();
        System.out.println("=== Synchronized counter ===");
        SynchronizedCounterDemo.run();

        System.out.println();
        System.out.println("=== Executor service ===");
        ExecutorServiceDemo.run();

        System.out.println();
        System.out.println("=== Producer consumer ===");
        ProducerConsumerDemo.run();
    }
}
