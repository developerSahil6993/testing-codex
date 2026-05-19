package com.example.multithreading;

import java.util.ArrayList;
import java.util.List;

public final class SynchronizedCounterDemo {
    private static final int THREAD_COUNT = 4;
    private static final int INCREMENTS_PER_THREAD = 10_000;

    private SynchronizedCounterDemo() {
    }

    public static void run() throws InterruptedException {
        SafeCounter counter = new SafeCounter();
        List<Thread> threads = new ArrayList<>();

        for (int index = 0; index < THREAD_COUNT; index++) {
            Thread thread = new Thread(() -> incrementManyTimes(counter), "safe-counter-" + index);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int expected = THREAD_COUNT * INCREMENTS_PER_THREAD;
        System.out.printf("Expected: %d, actual with synchronization: %d%n", expected, counter.value());
    }

    private static void incrementManyTimes(SafeCounter counter) {
        for (int count = 0; count < INCREMENTS_PER_THREAD; count++) {
            counter.increment();
        }
    }

    private static final class SafeCounter {
        private int value;

        synchronized void increment() {
            value++;
        }

        synchronized int value() {
            return value;
        }
    }
}
