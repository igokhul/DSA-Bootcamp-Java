import java.util.ArrayList;
import java.util.List;

public class SubSequenceOfArray {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5 };
        generateSubsequence(nums);
        String string = "abcded";
        System.out.println(string.substring(0, string.length() - 1));
    }

    public static void generateSubsequence(int[] nums) {
        helperRecursion(nums, 0, new ArrayList<>());
    }

    public static void helperRecursion(int[] nums, int index, List<Integer> list) {
        if (index == nums.length) {
            System.out.println(list);
            return;
        }

        // Take It
        list.add(nums[index]);
        helperRecursion(nums, index + 1, list);
        list.remove(list.size() - 1);

        // Leave It
        helperRecursion(nums, index + 1, list);
    }
}
