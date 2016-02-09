import p2.sorts.HeapSort;

public class HeapSortPersonalTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(integer_sorted());
        System.out.println(integer_random());
    }
    
    public static int integer_sorted() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] arr_sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        HeapSort.sort(arr, (i1, i2) -> i1.compareTo(i2));
        for(int i = 0; i < arr.length; i++) {
            if(!arr[i].equals(arr_sorted[i]))
                return 0;
        }
        return 1;
    }

    public static int integer_random() {
        Integer[] arr = {3, 1, 4, 5, 9, 2, 6, 7, 8};
        Integer[] arr_sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        HeapSort.sort(arr, (i1, i2) -> i1.compareTo(i2));
        for(int i = 0; i < arr.length; i++) {
            if(!arr[i].equals(arr_sorted[i]))
                return 0;
        }
        return 1;
    }
}
