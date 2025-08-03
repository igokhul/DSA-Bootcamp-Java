import java.util.HashMap;
import java.util.Map;

public class LongestHarmoniousSubsequence {
    public static void main(String[] args) {
        int[] nums = { 1, 3, 2, 2, 5, 2, 3, 7 };
        System.out.println(findLHS(nums));
    }

    public static int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int val : nums) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        int frequency = 0;
        for (int val : map.keySet()) {
            int currFrequency = 0;
            if (map.containsKey(val + 1)) {
                // there's a possibility of max value
                currFrequency = map.get(val) + map.get(val + 1);
                frequency = Math.max(frequency, currFrequency);
            }
        }

        return frequency;
    }
}
