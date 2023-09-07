package concurrency.chapter7.concept3;


import java.util.concurrent.*;

public class ThreadPoolExample {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new CustomThreadFactory());

        // This task will fail and trigger the UncaughtExceptionHandler.
        executor.execute(() -> {
            throw new RuntimeException("This is a runtime exception in a thread!");
        });

        // This task will fail but the exception will be captured by the Future object.
        Future<?> future = executor.submit(() -> {
            throw new RuntimeException("This exception is captured by Future.");
        });

        try {
            future.get();
        } catch (ExecutionException e) {
            System.err.println("Exception from task captured by Future: " + e.getCause().getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        executor.shutdown();
    }
}
