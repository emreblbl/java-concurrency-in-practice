package concurrency.chapter2.concept4;

public class EscapingExample {
    public static void main(String[] args) {
        EventManager manager = new EventManager();
        new Thread(() -> {
            new EscapingObject(manager);
        }).start();

        // Another thread trying to notify listeners before the object is fully constructed
        new Thread(() -> {
            try {
                Thread.sleep(500); // This thread waits less than the initialize() method duration
                manager.notifyListeners();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
