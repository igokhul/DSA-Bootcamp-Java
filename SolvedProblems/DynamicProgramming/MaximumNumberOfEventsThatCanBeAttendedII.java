import java.util.Arrays;
import java.util.Comparator;

public class MaximumNumberOfEventsThatCanBeAttendedII {
    public static void main(String[] args) {
        int[][] events = {
                { 1, 2, 4 },
                { 3, 4, 3 },
                { 2, 3, 10 }
        };
        int k = 3;

        System.out.println(maxValueRecursion(events, k));
        System.out.println(maxValueMemoization(events, k));
        System.out.println(maxValueDp(events, k));

    }

    public static int maxValueRecursion(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        return helperResursion(events, k, 0, 0, 0);
    }

    public static int helperResursion(int[][] events, int k, int index, int time, int start) {
        if (k == 0 || index == events.length) {
            return 0;
        }

        // take the current event
        int takeIt = Integer.MIN_VALUE;

        if (events[index][0] >= start) {
            takeIt = events[index][2]
                    + helperResursion(events, k - 1, index + 1, events[index][1],
                            events[index][1] + 1);
        }

        // leave the current event
        int leaveIt = helperResursion(events, k, index + 1, time, start);

        return Math.max(takeIt, leaveIt);
    }

    public static int maxValueMemoization(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int[][] memo = new int[events.length][k + 1];
        for (int[] is : memo) {
            Arrays.fill(is, -1);
        }
        return helperMemoization(events, k, 0, 0, 0, memo);
    }

    public static int helperMemoization(int[][] events, int k, int index, int time, int start, int[][] memo) {
        if (k == 0 || index == events.length) {
            return 0;
        }

        if (memo[index][k] != -1) {
            return memo[index][k];
        }

        int takeIt = Integer.MIN_VALUE;

        if (events[index][0] >= start) {
            takeIt = events[index][2]
                    + helperMemoization(events, k - 1, index + 1, events[index][1], events[index][1] + 1, memo);
        }

        int leaveIt = helperMemoization(events, k, index + 1, time, start, memo);

        return Math.max(takeIt, leaveIt);
    }

    public static int maxValueDp(int[][] events, int k) {
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
        int[][] dp = new int[events.length + 1][k + 1];

        for (int i = events.length - 1; i >= 0; i--) {
            int start = findNext(events, i);
            for (int j = 1; j <= k; j++) {
                int leaveIt = dp[i + 1][j];
                int takeIt = Integer.MIN_VALUE;
                takeIt = events[i][2] + dp[start][j - 1];
                dp[i][j] = Math.max(takeIt, leaveIt);
            }
        }

        return dp[0][k];
    }

    public static int findNext(int[][] events, int i) {
        int start = i + 1;
        int end = events.length;
        int target = events[i][1];

        while (start < end) {
            int mid = (start + end) / 2;

            if (events[mid][0] > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}
