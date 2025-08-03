import java.util.*;

public class FindTheKthCharacterInStringGameII {
    public static void main(String[] args) {
        long k = 25946251;
        int[] operations = { 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0 };
        System.out.println(kthCharacter(k, operations));
    }

    public static char kthCharacter(long k, int[] operations) {
        int countOperations = 0;
        long value = k;
        while (value > 1) {
            int jumps = (int) Math.ceil(Math.log(value) / Math.log(2));
            value = value - (long) Math.pow(2, jumps - 1);
            countOperations += operations[jumps - 1];
        }
        return (char) ('a' + (countOperations % 26));
    }
}
