import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindingPairsWithACertainSum {
    public static void main(String[] args) {
        int[] num1 = { 1, 1, 2, 2, 2, 3 };
        int[] num2 = { 1, 4, 5, 2, 5, 4 };
        // ["FindSumPairs","count","add","count","count","add","add","count"]
        FindSumPairs obj = new FindSumPairs(num1, num2);
        System.out.println(obj.count(7));
        obj.add(3, 2);
        System.out.println(obj.count(8));
        System.out.println(obj.count(4));
        obj.add(0, 1);
        obj.add(1, 1);
        System.out.println(obj.count(7));
    }
}

class FindSumPairs {
    int[] num1;
    int[] num2;
    Map<Integer, Integer> frequency;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.num1 = nums1;
        this.num2 = nums2;
        this.frequency = new HashMap<>();
        for (int n : nums2) {
            frequency.put(n, frequency.getOrDefault(n, 0) + 1);
        }
    }

    public void add(int index, int val) {
        int number = num2[index] + val;
        frequency.put(number, frequency.getOrDefault(number, 0) + 1);
        frequency.put(num2[index], frequency.get(num2[index]) - 1);
        num2[index] = number;
    }

    public int count(int tot) {
        int c = 0;
        for (int i = 0; i < num1.length; i++) {
            int val = tot - num1[i];
            c += frequency.getOrDefault(val, 0);
        }
        return c;
    }
}