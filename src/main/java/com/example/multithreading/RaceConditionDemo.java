package com.example.multithreading;

import java.util.ArrayList;
import java.util.List;

public final class RaceConditionDemo {
    private static final int THREAD_COUNT = 4;
    private static final int INCREMENTS_PER_THREAD = 10_000;

    private RaceConditionDemo() {
    }

    public static void run() throws InterruptedException {
        UnsafeCounter counter = new UnsafeCounter();
        List<Thread> threads = new ArrayList<>();

        for (int index = 0; index < THREAD_COUNT; index++) {
            Thread thread = new Thread(() -> incrementManyTimes(counter), "unsafe-counter-" + index);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int expected = THREAD_COUNT * INCREMENTS_PER_THREAD;
        System.out.printf("Expected: %d, actual without synchronization: %d%n", expected, counter.value());
    }

    private static void incrementManyTimes(UnsafeCounter counter) {
        for (int count = 0; count < INCREMENTS_PER_THREAD; count++) {
            counter.increment();
        }
    }

    private static final class UnsafeCounter {
        private int value;

        void increment() {
            value++;
        }

        int value() {
            return value;
        }
    }
}
