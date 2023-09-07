package concurrency.chapter8.concept1;

import java.util.concurrent.*;

public class TaskExecutionDemo {

    public static void main(String[] args) throws Exception {
        // Dependent Tasks:
        dependentTasksDemo();

        // Response-time-sensitive Tasks:
        responseTimeSensitiveTasksDemo();

        // Usage of ThreadLocal:
        threadLocalDemo();
    }

    private static void dependentTasksDemo() throws ExecutionException, InterruptedException {
        System.out.println("Dependent Tasks Demo:");

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = executor.submit(() -> {
            return 5 + 5;
        });

        Future<Integer> future2 = executor.submit(() -> {
            try {
                return future1.get() * 2;  // Depends on the result of future1.
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(future2.get());
        System.out.println();
    }


    private static void responseTimeSensitiveTasksDemo() {
        System.out.println("Response-time-sensitive Tasks Demo:");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> {
            System.out.println("Quick task completed.");
        });

        executor.execute(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Long task completed.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println();
    }

    private static void threadLocalDemo() {
        System.out.println("ThreadLocal Demo:");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "Initial Value");

        executor.execute(() -> {
            threadLocal.set("Thread A's Value");
            System.out.println(threadLocal.get());
        });

        executor.execute(() -> {
            System.out.println(threadLocal.get());
        });
    }
}
