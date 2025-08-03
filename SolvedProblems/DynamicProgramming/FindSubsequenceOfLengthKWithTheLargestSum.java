import java.util.*;

public class FindSubsequenceOfLengthKWithTheLargestSum {
    public static void main(String[] args) {
        int[] nums = { 3, 4, 3, 3 };
        int k = 2;

        maxSubsequence(nums, k);
    }

    public static int[] maxSubsequence(int[] nums, int k) {
        int[] arr = new int[k];
        return helperRecursion(0, arr, Integer.MIN_VALUE, nums, k);
    }

    public static int[] helperRecursion(int index, int[] arr, int max, int[] nums, int k) {
        if (k == 0) {
            return 
        }
    }
}
