package concurrency.chapter2.concept1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.List;

public class AtomicVsNonAtomic {

    private static AtomicInteger atomicCounter = new AtomicInteger(0);
    private static int nonAtomicCounter = 0;

    public static void main(String[] args) throws InterruptedException {
        testAtomicIncrement();
        Thread.sleep(1000);  // Just to give a small gap in the console output.
        testNonAtomicIncrement();
    }

    public static void testAtomicIncrement() {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicCounter.incrementAndGet();
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("[Atomic] Counter value: " + atomicCounter.get());
    }

    public static void testNonAtomicIncrement() {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    nonAtomicCounter++;
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("[Non-Atomic] Counter value: " + nonAtomicCounter);
    }
}
