package jabre.adventofcode.year2015.day11;

import jabre.adventofcode.Solution;

import java.util.Arrays;

public class Part1 implements Solution {
    static final Rule increasingStraightRule = (String s) -> {
        if(s.length() < 3) return false;

        for(int i = 0; i < s.length() - 2; i++) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(i+1);
            char c3 = s.charAt(i+2);
            if(c2 == c1 + 1 && c3 == c2 + 1) {
                return true;
            }
        }

        return false;
    };

    static final Rule forbiddenLettersRule = (String s) -> {
        for(char c: s.toCharArray()) {
            if(c == 'i' || c == 'o' || c == 'l') return false;
        }
        return true;
    };

    static final Rule repeatingDigitsRule = (String s) -> {
        char n1 = '\0', n2 = '\0';

        for(int i = 0; i < s.length() - 1;) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(i+1);

            if(c1 == c2) {
                if(n1 != '\0') {
                    if(c1 != n2) {
                        return true;
                    }
                    i += 2;
                } else {
                    n1 = c1;
                    i += 2;
                }
            } else {
                i++;
            }
        }
        return false;
    };

    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        String start = "hxbxwxba";
        return findNext(start);
    }

    static String findNext(String current) {
        do {
            current = increment(current);
        } while(!(increasingStraightRule.satisfies(current) && forbiddenLettersRule.satisfies(current) && repeatingDigitsRule.satisfies(current)));
        return current;
    }

    static String increment(String s) {
        char[] array = s.toCharArray();
        char[] newArray = new char[array.length + 1];
        boolean wrapAround = false;
        boolean first = true;

        for (int i = array.length - 1; i >= 0; i--) {
            int incrementAmount = (first ? 1 : 0) + (wrapAround ? 1 : 0);


            char current = array[i];
            if (current == 'z') {
                if (incrementAmount == 1) {
                    newArray[i + 1] = 'a';
                    wrapAround = true;
                } else if (incrementAmount == 2) {
                    newArray[i + 1] = 'b';
                    wrapAround = true;
                } else {
                    newArray[i+1] = 'z';
                }
            } else if (current == 'y') {
                if (incrementAmount == 1) {
                    newArray[i + 1] = 'z';
                    wrapAround = false;
                } else if (incrementAmount == 2) {
                    newArray[i + 1] = 'a';
                    wrapAround = true;
                } else {
                    newArray[i+1] = 'y';
                }
            } else {
                newArray[i + 1] = (char) (current + incrementAmount);

                wrapAround = false;
            }

            first = false;
        }

        if (wrapAround) {
            newArray[0] = 'a';
        }

        return new String(wrapAround ? newArray : Arrays.copyOfRange(newArray, 1, newArray.length));
    }

    interface Rule {
        boolean satisfies(String s);
    }
}
