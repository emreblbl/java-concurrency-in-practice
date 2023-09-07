package concurrency.chapter5.concept2.concurrentHashMap;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapVSConcurrentHashMap {
    public static void main(String[] args) throws InterruptedException {

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "one");
        hashMap.put("2", "two");
        hashMap.put("3", "three");

        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1", "one");
        concurrentHashMap.put("2", "two");
        concurrentHashMap.put("3", "three");

        // Thread continuously modifying hashMap
        new Thread(() -> {
            int count = 4;
            while (true) {
                try {
                    Thread.sleep(10);  // Short sleep to occasionally allow iteration
                    hashMap.put(String.valueOf(count), "number-" + count);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Try iterating over hashMap
        try {
            Thread.sleep(100);  // Give the modifying thread a little time to start
            for (String key : hashMap.keySet()) {
                System.out.println("HashMap Value: " + hashMap.get(key));
                Thread.sleep(100);  // Simulate some work during iteration
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Caught ConcurrentModificationException from HashMap");
        }

        // Thread continuously modifying concurrentHashMap
        new Thread(() -> {
            int count = 4;
            while (true) {
                try {
                    Thread.sleep(10);
                    concurrentHashMap.put(String.valueOf(count), "number-" + count);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Try iterating over concurrentHashMap
        Thread.sleep(100);  // Give the modifying thread a little time to start
        for (String key : concurrentHashMap.keySet()) {
            System.out.println("ConcurrentHashMap Value: " + concurrentHashMap.get(key));
            Thread.sleep(100);  // Simulate some work during iteration
        }
    }
}
