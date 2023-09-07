package concurrency.chapter7.concept3;

import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler(new SimpleUncaughtExceptionHandler());
        return thread;
    }
}
