package concurrency;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) {

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("orange", 3);

        Iterator<String> iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            Integer value = map.get(key);
            System.out.println(key + " = " + value);
            map.put("grape", 4); // modification during iteration
        }
    }
}
