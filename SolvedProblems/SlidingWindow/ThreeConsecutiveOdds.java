public class ThreeConsecutiveOdds {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 34, 3, 4, 5, 7, 23, 12 };
        System.out.println(threeConsecutiveOdds(nums));
    }

    public static boolean threeConsecutiveOdds(int[] nums) {
        int start = 0;
        int end = 0;
        int index = 0;

        while (start < nums.length && end < nums.length) {
            if (index == nums.length) {
                return end - start == 3;
            }

            if (nums[index] % 2 != 0) {
                end++;
            } else {
                start = index + 1;
                end = start;
            }

            if (end - start == 3) {
                return true;
            }

            index++;
        }

        return false;
    }
}
