package experiment;

import java.util.Comparator;


public class HeapSort {
    public static <E extends Comparable<E>> int  sort(E[] array) {
        return sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> int sort(E[] array, Comparator<E> comparator) {
        MinFourHeap<E> heap = new MinFourHeap<E>(comparator);
        for(int i = 0; i < array.length; i++) {
            heap.add(array[i]);
        }
        for(int i = 0; i < array.length; i++) {
            array[i] = heap.next();
        }
        return heap.step;
    }
}
