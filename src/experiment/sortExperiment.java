package experiment;

import java.util.Random;

public class sortExperiment {

    private static final int SIZE = 50000;
    private static final Random rand = new Random();
    private static final int K = 1000;
    
    public static void main(String[] args) {
        
        Integer[] nums = new Integer[SIZE];
        //heap sort
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(10000);
        }
        System.out.println("heap sort steps: " + HeapSort.sort(nums));
        
        // quick sort 
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(10000);
        }
        System.out.println("quick sort steps: " + QuickSort.sort(nums));
        
        // top k 
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(10000);
        }
        System.out.println("top k sort with K = " + K + " steps: " + TopKSort.sort(nums, K));
        
        // insertion sort
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(10000);
        }
        System.out.println("insertion sort steps: " + InsertionSort.sort(nums));
    }

}
