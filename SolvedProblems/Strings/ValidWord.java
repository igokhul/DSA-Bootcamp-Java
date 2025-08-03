import java.util.*;

public class ValidWord {
    public static void main(String[] args) {
        String s = "y0Ap";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String word) {
        if (word.length() < 3) {
            return false;
        }

        boolean isConsonent = false;
        boolean isVowel = false;
        for (char c : word.toCharArray()) {
            if (c < '0' || c > 'z' || (c > 'Z' && c < 'a') || (c > '9' && c < 'A')) {
                return false;
            }

            switch (c) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                    isVowel = true;
                    break;
                default:
                    if (!(c >= '0' && c <= '9')) {
                        isConsonent = true;
                    }
            }

        }

        return isVowel && isConsonent;
    }
}