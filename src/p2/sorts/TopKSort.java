package p2.sorts;

import java.util.Comparator;
import cse332.exceptions.NotYetImplementedException;
import datastructures.worklists.MinFourHeap;

public class TopKSort {
    public static <E extends Comparable<E>> void sort(E[] array, int k) {
        sort(array, k, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, int k, Comparator<E> comparator) {
        MinFourHeap<E> heap = new MinFourHeap<E>(comparator);
        for(int i = 0; i < array.length; i++) {
            if(heap.size() >= k) {
                if(comparator.compare(heap.peek(), array[i]) < 0) {
                    heap.next();
                    heap.add(array[i]);
                }
            } else {
                heap.add(array[i]);
            }
        }
        for(int i = 0; i < k; i++) {
            array[i] = heap.next();
        }
        for(int i = k; i < array.length; i++) {
            array[i] = null;
        }
    }
}
