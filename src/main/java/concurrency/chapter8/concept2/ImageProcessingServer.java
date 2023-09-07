package concurrency.chapter8.concept2;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ImageProcessingServer {

    private static final double UCPU = 0.9;      // Target CPU utilization
    private static final double W_C_RATIO = 0.25; // Wait time to Compute time ratio

    public static void main(String[] args) {
        int nCpu = Runtime.getRuntime().availableProcessors();
        int idealThreadPoolSize = calculateThreadPoolSize(nCpu, UCPU, W_C_RATIO);
        System.out.println("Number of available processors: " + nCpu);
        System.out.println("Ideal thread pool size: " + idealThreadPoolSize);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(idealThreadPoolSize);

        // Submit tasks for image processing and saving
//        for (int i = 0; i < 100; i++) {
//            executor.submit(new ImageTask());
//        }

        // Gracefully shutdown the executor
        executor.shutdown();
    }

    private static int calculateThreadPoolSize(int nCpu, double uCpu, double wCRatio) {
        return (int) Math.ceil(nCpu * uCpu * (1 + wCRatio));
    }

    static class ImageTask implements Runnable {

        @Override
        public void run() {
            processImage();
            saveImage();
        }

        private void processImage() {
            // Simulate compute-intensive task
            try {
                Thread.sleep(80); // Represents 80% of the task's time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void saveImage() {
            // Simulate I/O-bound task
            try {
                Thread.sleep(20); // Represents 20% of the task's time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
