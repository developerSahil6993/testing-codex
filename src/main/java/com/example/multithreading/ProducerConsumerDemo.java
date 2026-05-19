package com.example.multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ProducerConsumerDemo {
    private ProducerConsumerDemo() {
    }

    public static void run() throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(() -> produce(queue), "producer");
        Thread consumer = new Thread(() -> consume(queue), "consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }

    private static void produce(BlockingQueue<Integer> queue) {
        try {
            for (int value = 1; value <= 5; value++) {
                queue.put(value);
                System.out.println("Produced " + value);
                Thread.sleep(75);
            }

            queue.put(-1);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Producer interrupted", exception);
        }
    }

    private static void consume(BlockingQueue<Integer> queue) {
        try {
            while (true) {
                int value = queue.take();

                if (value == -1) {
                    break;
                }

                System.out.println("Consumed " + value);
                Thread.sleep(120);
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Consumer interrupted", exception);
        }
    }
}
