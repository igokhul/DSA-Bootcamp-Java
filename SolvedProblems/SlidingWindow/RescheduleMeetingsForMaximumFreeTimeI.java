import java.util.*;

public class RescheduleMeetingsForMaximumFreeTimeI {
    public static void main(String[] args) {
        int eventTime = 5;
        int k = 1;
        int[] startTime = { 2, 7, 13, 20 };
        int[] endTime = { 4, 10, 17, 24 };
        System.out.println(maxFreeTime(eventTime, k, startTime, endTime));
    }

    public static int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int lastEnd = 0;
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;
        Deque<Integer> deque = new ArrayDeque<>();
        boolean addEndTime = (endTime[endTime.length - 1] != eventTime) ? true : false;

        for (int i = 0; i < startTime.length; i++) {
            int currentSpace = startTime[i] - lastEnd;
            windowSum = windowSum + currentSpace;
            deque.addLast(currentSpace);
            maxSum = Math.max(maxSum, windowSum);

            if (deque.size() > k) {
                // Adjust the deque and window size to make not more than k moves
                int removedSpace = deque.removeFirst();
                windowSum = windowSum - removedSpace;
            }
            lastEnd = endTime[i];
        }

        if (addEndTime) {
            int currentSpace = eventTime - lastEnd;
            deque.addLast(currentSpace);
            windowSum = windowSum + currentSpace;
            deque.addLast(currentSpace);
            maxSum = Math.max(maxSum, windowSum);

            if (deque.size() > k) {
                // Adjust the deque and window size to make not more than k moves
                int removedSpace = deque.remove();
                windowSum = windowSum - removedSpace;
            }
        }

        return maxSum;
    }
}
