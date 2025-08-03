import java.util.*;

public class FindLuckyIntegerInAnArray {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 2, 3, 3, 3 };
        System.out.println(findLucky(arr));
    }

    public static int findLucky(int[] arr) {
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int num : arr) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        int max = Integer.MIN_VALUE;

        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();
            if (num == freq) {
                max = (max < num) ? num : max;
            }
        }

        return (max == Integer.MIN_VALUE) ? -1 : max;
    }
}
