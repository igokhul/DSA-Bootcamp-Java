import java.util.*;

public class FindTheOriginalTypedStringI {
    public static void main(String[] args) {
        String word = "abbcccc";
        System.out.println(possibleStringCount(word));

    }

    public static int possibleStringCount(String word) {
        int result = 0;
        int start = 0;
        int end = 0;

        while (end < word.length()) {
            while (end < word.length() && word.charAt(start) == word.charAt(end)) {
                end++;
            }
            result += end - start - 1;
            start = end;
        }

        return result + 1;
    }
}
