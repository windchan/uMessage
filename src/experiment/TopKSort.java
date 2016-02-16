package experiment;

import java.util.Comparator;

public class TopKSort {
    public static <E extends Comparable<E>> int sort(E[] array, int k) {
        return sort(array, k, (x, y) -> x.compareTo(y));
    }

    public static <E> int sort(E[] array, int k, Comparator<E> comparator) {
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
        if(k > array.length - 1) {
            for(int i = 0; i < array.length; i++) {
                array[i] = heap.next();
            }
        } else {
            for(int i = 0; i < k; i++) {
                array[i] = heap.next();
            }
            for(int i = k; i < array.length; i++) {
                array[i] = null;
            }
        }   
        return heap.step;
    }
}
