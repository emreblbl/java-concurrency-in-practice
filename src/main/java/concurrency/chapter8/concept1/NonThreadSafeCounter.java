package concurrency.chapter8.concept1;

public class NonThreadSafeCounter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int value() {
        return count;
    }
}
