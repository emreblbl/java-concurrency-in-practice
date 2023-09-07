package concurrency.chapter8.concept5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrencyExample {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> elements = List.of(1, 2, 3, 4, 5);

        System.out.println("Starting sequential processing...");
        long start = System.currentTimeMillis();
        processSequentially(elements);
        long end = System.currentTimeMillis();
        System.out.println("Sequential processing took: " + (end - start) + " ms");

        System.out.println("Starting parallel processing...");
        start = System.currentTimeMillis();
        processInParallel(elements);
        end = System.currentTimeMillis();
        System.out.println("Parallel processing took: " + (end - start) + " ms");
    }

    public static void processSequentially(List<Integer> elements) {
        for (Integer element : elements) {
            processElement(element);
        }
    }

    public static void processInParallel(List<Integer> elements) throws InterruptedException {
        int numThreads = elements.size(); // assuming we have as many threads as elements for this example
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        List<Runnable> tasks = new ArrayList<>();
        for (Integer element : elements) {
            tasks.add(() -> processElement(element));
        }

        for (Runnable task : tasks) {
            executor.submit(task);
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    public static void processElement(int element) {
        System.out.println("Processing element: " + element);
        try {
            Thread.sleep(1000); // simulate some computation by sleeping for a second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

