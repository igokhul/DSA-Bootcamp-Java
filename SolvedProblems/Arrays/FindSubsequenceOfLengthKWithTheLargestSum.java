import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;;

public class FindSubsequenceOfLengthKWithTheLargestSum {
    public static void main(String[] args) {
        int[] nums = { -1, -2, 3, 4 };
        int k = 3;
        System.out.println(Arrays.toString(maxSubsequence(nums, k)));
    }

    public static int[] maxSubsequence(int[] nums, int k) {
        List<int[]> values = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            values.add(new int[] { i, nums[i] });
        }

        // Sort the values list with the value of nums[i]
        values.sort((a, b) -> Integer.compare(b[1], a[1]));

        // Get the first top k elements
        List<int[]> topK = values.subList(0, k);

        // Sort the top k elements with their index positions
        topK.sort(Comparator.comparingInt(a -> a[0]));

        int[] result = new int[k];
        for (int i = 0; i < result.length; i++) {
            result[i] = topK.get(i)[1];
        }

        return result;
    }
}
