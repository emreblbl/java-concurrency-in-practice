package concurrency.chapter7.concept4;

public class ShutdownExample {

    public static void main(String[] args) {

        // Create a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown Hook is running!");
            // Here, you might perform cleanup tasks, close resources, etc.
        }));

        // Create a daemon thread
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("Daemon thread is running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DaemonThread");
        daemonThread.setDaemon(true);  // Set it as daemon
        daemonThread.start();

        // Create a non-daemon thread
        Thread nonDaemonThread = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                System.out.println("Non-daemon thread is running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "NonDaemonThread");
        nonDaemonThread.start();

        // Main thread completes
        System.out.println("Main thread is completing...");
    }
}

