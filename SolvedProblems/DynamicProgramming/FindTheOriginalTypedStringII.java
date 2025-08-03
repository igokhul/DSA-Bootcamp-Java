import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class FindTheOriginalTypedStringII {

    public static void main(String[] args) {
        String word = "bbbbbyyyyyyyyyyccccccccyyyqqqqhffffhhhhhhhhsswwwwvvvvvlllldddddddddnnnnnnvr";
        int k = 69;
        // System.out.println(possibleStringCount(word, k));
        System.out.println(possibleStringCountMemo(word, k));
        System.out.println(possibleStringCountDp(word, k));
        System.out.println(possibleStringCountDpWithPrefixSum(word, k));
    }

    static int M = 1000000007;

    public static int possibleStringCount(String word, int k) {
        if (word.length() < k) {
            return 0;
        }

        List<Integer> frequency = new ArrayList();
        int count = 1;

        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            } else {
                frequency.add(count);
                count = 1;
            }
        }
        frequency.add(count);

        long totalPossibleAnswer = 1;
        for (int f : frequency) {
            totalPossibleAnswer = (totalPossibleAnswer * f) % M;
        }

        if (frequency.size() > k) {
            return (int) totalPossibleAnswer;
        }

        int invalidCount = helper(0, 0, frequency, k);

        return (int) (totalPossibleAnswer - invalidCount + M) % M;
    }

    public static int helper(int index, int count, List<Integer> frequency, int k) {
        if (index >= frequency.size()) {
            if (count < k) {
                return 1;
            }
            return 0;
        }

        int result = 0;

        for (int take = 1; take <= frequency.get(index); take++) {
            if (count + take < k) {
                result = (result + helper(index + 1, count + take, frequency, k)) % M;
            } else {
                break;
            }
        }

        return result;
    }

    public static int possibleStringCountMemo(String word, int k) {
        if (word.length() < k) {
            return 0;
        }

        List<Integer> frequency = new ArrayList();
        int count = 1;

        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            } else {
                frequency.add(count);
                count = 1;
            }
        }
        frequency.add(count);

        long totalPossibleAnswer = 1;
        for (int f : frequency) {
            totalPossibleAnswer = (totalPossibleAnswer * f) % M;
        }

        if (frequency.size() > k) {
            return (int) totalPossibleAnswer;
        }

        int[][] memo = new int[frequency.size() + 1][k + 1];
        for (int[] is : memo) {
            Arrays.fill(is, -1);
        }

        int invalidCount = helperMemo(0, 0, frequency, k, memo);

        return (int) (totalPossibleAnswer - invalidCount + M) % M;
    }

    public static int helperMemo(int index, int count, List<Integer> frequency, int k, int[][] memo) {
        if (index >= frequency.size()) {
            if (count < k) {
                return 1;
            }
            return 0;
        }

        if (memo[index][count] != -1) {
            return memo[index][count];
        }

        int result = 0;

        for (int take = 1; take <= frequency.get(index); take++) {
            if (count + take < k) {
                result = (result + helperMemo(index + 1, count + take, frequency, k, memo)) % M;
            } else {
                break;
            }
        }

        return memo[index][count] = result;
    }

    public static int possibleStringCountDp(String word, int k) {
        if (word.length() < k) {
            return 0;
        }

        List<Integer> frequency = new ArrayList();
        int count = 1;

        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            } else {
                frequency.add(count);
                count = 1;
            }
        }
        frequency.add(count);

        long totalPossibleAnswer = 1;
        for (int f : frequency) {
            totalPossibleAnswer = (totalPossibleAnswer * f) % M;
        }

        if (frequency.size() > k) {
            return (int) totalPossibleAnswer;
        }

        int[][] dp = new int[frequency.size() + 1][k + 1];

        for (int c = 0; c < k; c++) {
            dp[frequency.size()][c] = 1;
        }

        for (int i = frequency.size() - 1; i >= 0; i--) {
            for (int j = 0; j < k; j++) {
                int result = 0;
                for (int take = 1; take <= frequency.get(i); take++) {
                    if (j + take < k) {
                        result = (result + dp[i + 1][j + take]) % M;
                    } else {
                        break;
                    }
                }
                dp[i][j] = result;
            }
        }

        return (int) (totalPossibleAnswer - dp[0][0] + M) % M;
    }

    public static int possibleStringCountDpWithPrefixSum(String word, int k) {
        if (word.length() < k) {
            return 0;
        }

        List<Integer> frequency = new ArrayList();
        int count = 1;

        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            } else {
                frequency.add(count);
                count = 1;
            }
        }
        frequency.add(count);

        long totalPossibleAnswer = 1;
        for (int f : frequency) {
            totalPossibleAnswer = (totalPossibleAnswer * f) % M;
        }

        if (frequency.size() > k) {
            return (int) totalPossibleAnswer;
        }

        int[][] dp = new int[frequency.size() + 1][k + 1];

        for (int c = 0; c < k; c++) {
            dp[frequency.size()][c] = 1;
        }

        for (int i = frequency.size() - 1; i >= 0; i--) {

            int[] prefix = new int[k];
            prefix[0] = dp[i + 1][0];
            for (int c = 1; c < k; c++) {
                prefix[c] = (prefix[c - 1] + dp[i + 1][c]) % M;
            }

            for (int j = 0; j < k; j++) {
                int maxTake = Math.min(frequency.get(i), k - j - 1);
                if (maxTake < 1) {
                    dp[i][j] = 0;
                } else {
                    int left = j + 1;
                    int right = j + maxTake;
                    int sum = prefix[right];
                    if (left - 1 >= 0) {
                        sum = (sum - prefix[left - 1] + M) % M;
                    }
                    dp[i][j] = sum;
                }
            }
        }

        return (int) (totalPossibleAnswer - dp[0][0] + M) % M;
    }
}
