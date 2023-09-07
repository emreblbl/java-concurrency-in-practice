package concurrency.chapter5.concept2;

import java.util.*;
import java.util.concurrent.*;

/**
 * Comparison between ArrayList and CopyOnWriteArrayList
 */
public class ListComparison {

    public static void main(String[] args) throws InterruptedException {
        List<String> arrayList = new ArrayList<>();
        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        // Add some initial elements
        for (int i = 0; i < 10; i++) {
            arrayList.add("Item " + i);
            copyOnWriteArrayList.add("Item " + i);
        }

        // Scenario 1: Using ArrayList
        Thread readThread1 = new Thread(() -> {
            try {
                for (String item : arrayList) {
                    System.out.println("ArrayList Reading: " + item);
                    Thread.sleep(100);  // sleep to simulate some work
                }
            } catch (Exception e) {
                System.out.println("Exception in ArrayList: " + e.getClass().getSimpleName());
            }
        });

        Thread writeThread1 = new Thread(() -> {
            try {
                Thread.sleep(300);  // sleep to ensure the read thread starts reading first
                arrayList.add("New Item");
            } catch (InterruptedException ignored) {
            }
        });

        readThread1.start();
        writeThread1.start();
        readThread1.join();
        writeThread1.join();

        System.out.println("----------------------------");

        // Scenario 2: Using CopyOnWriteArrayList
        Thread readThread2 = new Thread(() -> {
            try {
                for (String item : copyOnWriteArrayList) {
                    System.out.println("CopyOnWriteArrayList Reading: " + item);
                    Thread.sleep(100);  // sleep to simulate some work
                }
            } catch (Exception e) {
                System.out.println("Exception in CopyOnWriteArrayList: " + e.getClass().getSimpleName());
            }
        });

        Thread writeThread2 = new Thread(() -> {
            try {
                Thread.sleep(300);  // sleep to ensure the read thread starts reading first
                copyOnWriteArrayList.add("New Item");
            } catch (InterruptedException ignored) {
            }
        });

        readThread2.start();
        writeThread2.start();
        readThread2.join();
        writeThread2.join();
    }
}

