import java.util.Comparator;

import p2.sorts.TopKSort;

public class TopKSortPersonalTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.print(integer_sorted());
        System.out.print(integer_random());
        System.out.println(-12 % 5);
    }
    
    public static int integer_sorted() {
        int K = 4;
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] arr_sorted = {7, 8, 9, 10};
        TopKSort.sort(arr, K, new Comparator<Integer>() {
            @Override
            public int compare(Integer e1, Integer e2) {
                return e1.compareTo(e2);
            }
        });
        for(int i = 0; i < K; i++) {
            if(!arr[i].equals(arr_sorted[i]))
                return 0;
        }
        return 1;
    }

    public static int integer_random() {
        int K = 4;
        Integer[] arr = {3, 1, 4, 5, 9, 2, 6, 7, 8};
        Integer[] arr_sorted = {6, 7, 8, 9};
        TopKSort.sort(arr, K, new Comparator<Integer>() {
            @Override
            public int compare(Integer e1, Integer e2) {
                return e1.compareTo(e2);
            }
        });
        for(int i = 0; i < K; i++) {
            if(!arr[i].equals(arr_sorted[i]))
                return 0;
        }
        return 1;
    }
}
