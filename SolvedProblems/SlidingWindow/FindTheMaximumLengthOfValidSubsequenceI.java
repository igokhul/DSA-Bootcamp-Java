import java.util.*;

public class FindTheMaximumLengthOfValidSubsequenceI {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 1, 1, 2, 1, 2 };
        System.out.println(maximumLength(nums));
    }

    public static int maximumLength(int[] nums) {
        int oddCount = 0;
        int evenCount = 0;
        int alternatingCount = 1;
        boolean expectingEven = false;

        if (nums[0] % 2 == 0) {
            evenCount++;
        } else {
            oddCount++;
            expectingEven = true;
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                evenCount++;
                if (expectingEven) {
                    alternatingCount++;
                    expectingEven = false;
                }
            } else {
                oddCount++;
                if (!expectingEven) {
                    alternatingCount++;
                    expectingEven = true;
                }
            }
        }

        return Math.max(oddCount, Math.max(evenCount, alternatingCount));
    }
}
