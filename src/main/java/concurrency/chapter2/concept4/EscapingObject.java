package concurrency.chapter2.concept4;

public class EscapingObject implements EventListener {
    private final EventManager manager;
    private int value = 0;

    public EscapingObject(EventManager manager) {
        this.manager = manager;
        manager.register(this);  // 'this' escapes here!
        initialize();
    }

    private void initialize() {
        try {
            Thread.sleep(1000);
            value = 42;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void onEvent() {
        System.out.println("Value is: " + value);
    }
}

