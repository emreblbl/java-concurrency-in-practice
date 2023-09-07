package concurrency.chapter6.concept1;

public class SequentialVSThreadBasedTask {
    private static void task(int id) {
        System.out.println("Task " + id + " started");
        try {
            Thread.sleep(2000); // Simulating some I/O-bound work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task " + id + " finished");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Sequential Execution:");
        long startTime = System.currentTimeMillis();
        sequentialExecution();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken for Sequential Execution: " + (endTime - startTime) + "ms");

        System.out.println("\nThread-per-Task Execution:");
        startTime = System.currentTimeMillis();
        threadedExecution();
        endTime = System.currentTimeMillis();
        System.out.println("Time taken for Thread-per-Task Execution: " + (endTime - startTime) + "ms");
    }

    private static void sequentialExecution() {
        for (int i = 0; i < 5; i++) {
            task(i);
        }
    }

    private static void threadedExecution() throws InterruptedException {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int id = i;
            threads[i] = new Thread(() -> task(id));
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
