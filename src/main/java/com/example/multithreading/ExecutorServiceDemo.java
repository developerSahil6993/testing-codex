package com.example.multithreading;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class ExecutorServiceDemo {
    private ExecutorServiceDemo() {
    }

    public static void run() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Callable<String>> tasks = List.of(
                () -> taskResult("download data"),
                () -> taskResult("process data"),
                () -> taskResult("save result")
        );

        try {
            List<Future<String>> results = executor.invokeAll(tasks);

            for (Future<String> result : results) {
                System.out.println(get(result));
            }
        } finally {
            executor.shutdown();
        }
    }

    private static String taskResult(String taskName) throws InterruptedException {
        Thread.sleep(150);
        return Thread.currentThread().getName() + " finished " + taskName;
    }

    private static String get(Future<String> future) {
        try {
            return future.get();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Thread was interrupted", exception);
        } catch (ExecutionException exception) {
            throw new IllegalStateException("Task failed", exception);
        }
    }
}
