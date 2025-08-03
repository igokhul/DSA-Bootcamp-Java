import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumNumberOfEventsThatCanBeAttended {
    public static void main(String[] args) {
        int[][] events = {
                { 1, 2 },
                { 2, 3 },
                { 3, 4 },
                { 1, 2 }
        };
        System.out.println(maxEvents(events));
    }

    public static int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int index = 0;
        int attended = 0;
        int time = 1;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        while (index < events.length || !minHeap.isEmpty()) {
            // skip time where you can't attend any event
            if (minHeap.isEmpty()) {
                time = Math.max(time, events[index][0]);
            }

            // Add events starting at current time
            while (index < events.length && events[index][0] == time) {
                minHeap.add(events[index][1]);
                index++;
            }

            // remove events that cannot be attended
            while (!minHeap.isEmpty() && minHeap.peek() < time) {
                minHeap.poll();
            }

            if (!minHeap.isEmpty()) {
                minHeap.poll();
                attended++;
            }

            time++;
        }

        return attended;
    }
}
