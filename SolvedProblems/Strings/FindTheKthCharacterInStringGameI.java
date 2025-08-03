import java.util.*;

public class FindTheKthCharacterInStringGameI {
    public static void main(String[] args) {
        int k = 5;
        System.out.println(kthCharacter(k));
    }

    public static char kthCharacter(int k) {
        StringBuilder sb = new StringBuilder("a");

        while (sb.length() < k) {
            StringBuilder newString = new StringBuilder();
            for (int i = 0; i < sb.length(); i++) {
                char c = sb.charAt(i);
                if (c == 'z') {
                    newString.append('a');
                } else {
                    newString.append((char) (c + 1));
                }

            }
            sb.append(newString);
        }

        return sb.charAt(k - 1);
    }
}
