package com.example.multithreading;

public final class ThreadBasics {
    private ThreadBasics() {
    }

    public static void run() throws InterruptedException {
        Thread worker = new Thread(() -> printNumbers("worker"), "practice-worker");

        worker.start();
        printNumbers("main");
        worker.join();
    }

    private static void printNumbers(String label) {
        for (int number = 1; number <= 3; number++) {
            System.out.printf("%s thread: %d%n", label, number);
            sleep(100);
        }
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Thread was interrupted", exception);
        }
    }
}
