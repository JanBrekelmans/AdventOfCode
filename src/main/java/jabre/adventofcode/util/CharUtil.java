package jabre.adventofcode.util;

public final class CharUtil {
    private CharUtil() {}

    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static boolean isVowel(int c) {
        return isVowel((char) c);
    }
}
