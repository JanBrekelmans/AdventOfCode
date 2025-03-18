package jabre.adventofcode.year2015.day10;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }
    @Override
    public String solve() {
        String current = "3113322113";

        for(int i = 0; i < 40; i++) {
            current = determineNextIteration(current);
        }
        return String.valueOf(current.length());
    }

    static String determineNextIteration(String current) {
        StringBuilder builder = new StringBuilder();
        for(var section: StringUtil.splitStringOnRepeatingCharacters(current)) {
            int number = section.charAt(0) - '0';
            int amount = section.length();
            builder.append(nextIterationForSection(number, amount));
        }
        return builder.toString();
    }

    static String nextIterationForSection(int number, int amount) {
        return "" + amount + "" + number;
    }

}
