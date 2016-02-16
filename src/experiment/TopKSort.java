package experiment;

import java.util.Comparator;

public class TopKSort {
    public static <E extends Comparable<E>> int sort(E[] array, int k) {
        return sort(array, k, (x, y) -> x.compareTo(y));
    }

    public static <E> int sort(E[] array, int k, Comparator<E> comparator) {
        MinFourHeap<E> heap = new MinFourHeap<E>(comparator);
        int steps = 0;
        for(int i = 0; i < array.length; i++) {
            if(heap.size() >= k) {
                if(comparator.compare(heap.peek(), array[i]) < 0) {
                    heap.next();
                    heap.add(array[i]);
                }
            } else {
                heap.add(array[i]);
            }
            steps += Math.ceil(HeapSort.log4(heap.size()));
        }
        if(k > array.length - 1) {
            for(int i = 0; i < array.length; i++) {
                steps += Math.ceil(HeapSort.log4(heap.size()));
                array[i] = heap.next();
            }
        } else {
            for(int i = 0; i < k; i++) {
                steps += Math.ceil(HeapSort.log4(heap.size()));
                array[i] = heap.next();
            }
            for(int i = k; i < array.length; i++) {
                array[i] = null;
                steps++;
            }
        }   
        return heap.step;
    }
}
