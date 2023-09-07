package concurrency.chapter6.concept3;

import java.util.Date;
import java.util.concurrent.*;

public class RunnableVsCallable {

    public static void main(String[] args) {
        // Runnable Task
        Runnable loggingTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("Current Date-Time: " + new Date());
            }
        };

        // Start a thread for the Runnable task
        new Thread(loggingTask).start();

        // Callable Task
        Callable<Long> factorialTask = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return factorial(5);  // Calculate factorial of 5
            }

            private Long factorial(int n) {
                if (n <= 1) return 1L;
                return n * factorial(n - 1);
            }
        };

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Long> futureResult = executorService.submit(factorialTask);

        try {
            Long result = futureResult.get();
            System.out.println("Factorial Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Task interrupted or failed: " + e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }
}
