import java.util.ArrayList;
import java.util.List;

public class FindTheMaximumLengthOfValidSubsequenceI {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 1, 1, 2, 1, 2 };
        System.out.println(maximumLengthRecursion(nums));
    }

    public static int maximumLengthRecursion(int[] nums) {
        return helperRecursion(nums, new ArrayList<>(), 0, -1);
    }

    public static int helperRecursion(int[] nums, List<Integer> list, int index, int isEvenSeries) {
        if (index == nums.length) {
            return list.size();
        }

        int takeIt = Integer.MIN_VALUE;
        int leaveIt = Integer.MIN_VALUE;

        // Take It
        if (isEvenSeries == -1) {
            takeIt = helperRecursion(nums, list, index + 1, (nums[i] % 2 == 0) ? 1 : 0);
        } else if (list.get(list.size() - 1) % 2 == 0 && isEvenSeries == 0) {
            takeIt = helperRecursion(nums, list, index + 1, 0);
        } else {
            takeIt = helperRecursion(nums, list, index + 1, 0);
        }

        // Leave It
        leaveIt = helperRecursion(nums, list, index + 1, isEvenSeries);

        return Math.max(takeIt, leaveIt);
    }
}
