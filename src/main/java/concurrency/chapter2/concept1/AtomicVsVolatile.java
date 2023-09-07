package concurrency.chapter2.concept1;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVsVolatile {
    private volatile int volatileCounter = 0;
    private AtomicInteger atomicCounter = new AtomicInteger(0);

    public void incrementVolatile() {
        volatileCounter++;
    }

    public void incrementAtomic() {
        atomicCounter.incrementAndGet();
    }

    public int getVolatileCounter() {
        return volatileCounter;
    }

    public int getAtomicCounter() {
        return atomicCounter.get();
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicVsVolatile counter = new AtomicVsVolatile();

        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.incrementVolatile();
                    counter.incrementAtomic();
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Volatile Counter value: " + counter.getVolatileCounter());
        System.out.println("Atomic Counter value: " + counter.getAtomicCounter());
    }
}


// Volatile limitations :

//Limitations:volatile only ensures visibility, not atomicity.
// So, operations like check-then-act or read-modify-write (e.g., count++ for a volatile count variable) are not atomic and can still suffer from race conditions.
//        It’s less flexible than other mechanisms.