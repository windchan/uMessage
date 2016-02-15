package experiment;

import java.util.Random;

public class sortExperiment {

    private static final int SIZE = 50000;
    private static final Random rand = new Random();
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Integer[] nums = new Integer[SIZE];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt();
        }
        System.out.println("heap sort steps: " + HeapSort.sort(nums));
    }

}
