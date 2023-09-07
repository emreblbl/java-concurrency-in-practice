package concurrency.chapter5.concept4;

public class BlockingAndInterruptibleExample {

    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();

        // Thread 1: Tries to acquire the lock and then goes into a WAITING state
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1 acquired the lock.");
                try {
                    // Puts thread1 into WAITING state (indefinitely)
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println("Thread 1 was interrupted while waiting!");
                }
            }
        });

        // Thread 2: Tries to acquire the same lock after a short sleep
        // Demonstrates the BLOCKED state
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(100); // Let thread1 acquire the lock first
            } catch (InterruptedException ignored) {}

            synchronized (lock) {
                System.out.println("Thread 2 acquired the lock.");
            }
        });

        thread1.start();
        thread2.start();

        // Sleep for a bit and then interrupt thread1
        Thread.sleep(200);
        thread1.interrupt();

        thread1.join();
        thread2.join();
    }
}

