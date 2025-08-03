import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RescheduleMeetingsForMaximumFreeTimeII {
    public static void main(String[] args) {
        int eventTime = 10;
        int[] startTime = { 0, 7, 9 };
        int[] endTime = { 1, 8, 10 };
        System.out.println(maxFreeTime(eventTime, startTime, endTime));
    }

    static class Pair {
        int space;
        int index;

        public Pair(int space, int index) {
            this.space = space;
            this.index = index;
        }
    }

    public static int maxFreeTime(int eventTime, int[] st, int[] et) {
        int[] startTime = Arrays.copyOf(st, st.length + 1);
        startTime[startTime.length - 1] = eventTime;
        int[] endTime = Arrays.copyOf(et, et.length + 1);
        endTime[endTime.length - 1] = eventTime;

        List<Pair> pairs = new ArrayList<>();
        pairs.add(new Pair(startTime[0], 0));
        pairs.add(new Pair(-1, -1));
        pairs.add(new Pair(-1, -1));

        // Find maximum gaps without moving any meetings
        for (int i = 1; i < startTime.length; i++) {
            Pair gap = new Pair(startTime[i] - endTime[i - 1], i);
            if (gap.space > pairs.get(2).space) {
                pairs.set(2, gap);
                Collections.sort(pairs, (a, b) -> b.space - a.space);
            }
        }

        int maxFreeTime = 0;
        for (int i = 0; i < startTime.length - 1; i++) {
            int currentFreeTime = getCurrentSpaceByRescheduling(i, pairs, startTime, endTime);
            maxFreeTime = Math.max(currentFreeTime, maxFreeTime);
        }

        return maxFreeTime;
    }

    public static int getCurrentSpaceByRescheduling(int i, List<Pair> pairs, int[] startTime, int[] endTime) {
        int lastEnd = (i == 0) ? 0 : endTime[i - 1];
        int meetingDuration = endTime[i] - startTime[i];
        int nextStart = startTime[i + 1];
        int originalGap = nextStart - lastEnd;

        for (Pair gap : pairs) {
            if (gap.space >= meetingDuration && gap.index != i && gap.index != i + 1) {
                return originalGap;
            }
        }

        return originalGap - meetingDuration;
    }
}
