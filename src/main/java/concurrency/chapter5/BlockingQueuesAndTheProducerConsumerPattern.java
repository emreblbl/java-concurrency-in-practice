package concurrency.chapter5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueuesAndTheProducerConsumerPattern {
    public static void main(String[] args) throws InterruptedException {
        // Create a blocking queue with a capacity of 5
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        // Create a producer thread that generates numbers from 0 to 9
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    // Add the number to the queue
                    queue.put(i);
                    System.out.println("Producer added: " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create a consumer thread that retrieves numbers from the queue
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    // Retrieve the next number from the queue
                    int num = queue.take();
                    System.out.println("Consumer removed: " + num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start the producer and consumer threads
        producer.start();
        consumer.start();

        // Wait for the threads to finish
        producer.join();
        consumer.join();
    }
}

