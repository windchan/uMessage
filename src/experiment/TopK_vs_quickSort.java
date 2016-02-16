package experiment;

import java.util.Random;

public class TopK_vs_quickSort {
    
    private static final int NUM_TESTS = 10;
    private static final int INR_N = 10000;
    private static final int INR_K = 500;
    private static final Random rnd = new Random();
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n = 100000;
        int k = 50000;
        for (int i = 0; i < NUM_TESTS; i++) {
            Integer[] nums = new Integer[n];
            // quick sort
            for (int j = 0; j < n; j++) {
                nums[j] = rnd.nextInt();
            }
            System.out.println("QuickSort: " + QuickSort.sort(nums));
            
            // topk
            for (int j = 0; j < n; j++) {
                nums[j] = rnd.nextInt();
            }

            System.out.println("TopKSort: " + TopKSort.sort(nums, k));
            System.out.println("N = " + n + ", K = " + k);
            System.out.println();
            k += INR_K;
            n += INR_N;
        }
    }

}
