package experiment;

import java.util.Comparator;
import datastructures.worklists.MinFourHeap;

public class HeapSort {
    public static <E extends Comparable<E>> long  sort(E[] array) {
        return sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> long sort(E[] array, Comparator<E> comparator) {
        MinFourHeap<E> heap = new MinFourHeap<E>(comparator);
        long step = 0;
        for(int i = 0; i < array.length; i++) {
            heap.add(array[i]);
            step += Math.ceil(log4(heap.size()));
        }
        for(int i = 0; i < array.length; i++) {
            step += Math.ceil(log4(heap.size()));
            array[i] = heap.next();
            
        }
        return step;
    }
    
    public static double log4(int n) {
        return Math.log10(n) / Math.log10(4);
    }
}
