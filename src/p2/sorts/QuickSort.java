package p2.sorts;

import java.util.Comparator;

import cse332.exceptions.NotYetImplementedException;

public class QuickSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        int lo = 0;
        int hi = array.length - 1;
        sort(array, lo, hi, comparator);
    }
    
    private static <E> void sort(E[] array, int lo, int hi, Comparator<E> comparator) { 
        if(lo < hi) {
            int i = lo;
            int front = lo + 1;
            int end = hi;
            E element = array[i];
            while(front <= end) {
                if(comparator.compare(array[front], element) >= 0) {
                    swap(array, front, end);
                    end--;
                } else {
                    swap(array, front, i);
                    front++;
                    i++;
                }
            }
            sort(array, lo, i, comparator);
            sort(array, i + 1, hi, comparator);
        }       
    }
    
    private static <E> void swap(E[] array, int a, int b) {
        E temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
