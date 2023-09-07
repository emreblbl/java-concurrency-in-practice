package concurrency.chapter2.concept4;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private final List<EventListener> listeners = new ArrayList<>();

    public void register(EventListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners() {
        for (EventListener listener : listeners) {
            listener.onEvent();
        }
    }
}

interface EventListener {
    void onEvent();
}


