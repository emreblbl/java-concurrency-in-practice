package concurrency.chapter5.concept2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Comparing that reflects the changes in CopyOnWriteArrayList
 */
public class ListComparison2 {
    public static void main(String[] args) {
        // Create an ArrayList and add some elements
        List<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        // Create a CopyOnWriteArrayList and add the same elements
        List<String> cowArrayList = new CopyOnWriteArrayList<>(arrayList);

        // Create an iterator for the CopyOnWriteArrayList
        Iterator<String> cowArrayIterator = cowArrayList.iterator();


        // Modify the CopyOnWriteArrayList by adding a new element
        cowArrayList.add("E");

        // Print the elements of the CopyOnWriteArrayList using the iterator
        System.out.println("Elements of CopyOnWriteArrayList:");
        while (cowArrayIterator.hasNext()) {
            System.out.println(cowArrayIterator.next());
        }
    }
}
