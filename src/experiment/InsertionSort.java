package experiment;

import java.util.Comparator;

public class InsertionSort {
    public static <E extends Comparable<E>> int sort(E[] array) {
        return sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> int sort(E[] array, Comparator<E> comparator) {
        int steps = 0;
        for (int i = 1; i < array.length; i++) {
            E x = array[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                steps++;
                if (comparator.compare(x, array[j]) >= 0) {
                    break;
                }
                array[j + 1] = array[j];
            }
            array[j + 1] = x;
        }
        return steps;
    }
}
