package concurrency.chapter2.concept3;

public class ThreadLocalExample {
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            threadLocal.set(10);
            System.out.println("Thread 1: " + threadLocal.get());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2: " + threadLocal.get());
            threadLocal.remove();
        });

        thread1.start();
        thread1.join();
        thread2.start();


        thread2.join();
    }
}

// NOTE: Removing the threadlocal variable

//Although ThreadLocal objects might be useful while developing a multithreaded application, it is really important to use
//it wisely and clear the ThreadLocal objects otherwise you might end up having a java.lang.OutOfMemoryError due to
//memory leaks in the case where you are using Thread Pool managed by the application servers.
