package concurrency.chapter7.concept3;

public class SimpleUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.err.println("Thread " + t.getName() + " encountered an exception: " + e.getMessage());
    }
}
