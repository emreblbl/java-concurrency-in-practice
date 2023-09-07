package concurrency.chapter5;


public class BlockingAndInterruptibleMethods implements Runnable {
    public void run() {
        try {
            // Do some work that may take a long time
            for (int i = 0; i < 10; i++) {
                System.out.println("Working " + i);
                Thread.sleep(1000); // wait for 1 second
            }
        } catch (InterruptedException e) {
            // Handle the interrupt by re-interrupting the thread or exiting gracefully
            System.out.println("Thread interrupted while sleeping, stopping work");
            Thread.currentThread().interrupt(); // re-interrupt the thread
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new BlockingAndInterruptibleMethods());
        t.start();

        // Wait for some time and then interrupt the thread
        Thread.sleep(5000); // wait for 5 seconds
        t.interrupt(); // interrupt the thread

        // Wait for the thread to finish
        t.join();
        System.out.println("Thread finished");
    }

}
