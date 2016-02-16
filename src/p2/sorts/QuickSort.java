package p2.sorts;

import java.util.Comparator;

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
            
            int i = findMedian(array, lo, hi - 1, lo + (hi - 1 - lo) / 2, comparator);
            int front = lo + 1;
            int end = hi;
            swap(array, lo, i);
            E pivot = array[lo];
            i = lo;
            while(front <= end) {
                if(comparator.compare(array[front], pivot) >= 0) {
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
    
    private static <E> int findMedian(E[] arr, int a, int b, int c, Comparator<E> comparator) {
        if(checkMedian(arr[a], arr[b], arr[c], comparator)) {
            return b;
        } else if (checkMedian(arr[b], arr[a], arr[c], comparator)) {
            return a;
        } else {
            return c;
        }
    }
    
    private static <E> boolean checkMedian(E a, E b, E c, Comparator<E> comparator) {
        return (comparator.compare(a, b) < 0 && comparator.compare(b, c) < 0) ||
        (comparator.compare(c, b) < 0 && comparator.compare(b, a) < 0);
    }
    private static <E> void swap(E[] array, int a, int b) {
        E temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
