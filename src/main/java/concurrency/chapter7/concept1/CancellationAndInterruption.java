package concurrency.chapter7.concept1;

public class CancellationAndInterruption {

    public static void main(String[] args) {
        Thread longRunningTask = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    // Simulating some work
                    System.out.println("Task is running...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Task was interrupted during sleep!");
                    // Restore the interrupted status
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Task is cleaning up and shutting down...");
        });

        longRunningTask.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread requests interruption of the long-running task.");
        longRunningTask.interrupt();

        try {
            longRunningTask.join(); // Wait for the long-running task to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread completes.");
    }
}

