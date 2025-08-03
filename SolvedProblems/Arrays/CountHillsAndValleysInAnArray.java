import java.util.*;

public class CountHillsAndValleysInAnArray {
    public static void main(String[] args) {
        int[] nums = { 2, 4, 1, 1, 6, 5 };
        System.out.println(countHillValley(nums));
    }

    public static int countHillValley(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        int hillOrValleyCount = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            int curr = nums[i];

            // Move left
            int j = i - 1;
            while (j >= 0 && nums[j] == curr) {
                j--;
            }

            if (j < 0) {
                continue;
            }

            // Move right
            int k = i + 1;
            while (k < nums.length && nums[k] == curr) {
                k++;
            }

            if (k >= nums.length) {
                continue;
            }

            if (((nums[j] > curr && nums[k] > curr) || (nums[j] < curr && nums[k] < curr)) && (j == i - 1)) {
                hillOrValleyCount++;
            }
        }

        return hillOrValleyCount;
    }
}
