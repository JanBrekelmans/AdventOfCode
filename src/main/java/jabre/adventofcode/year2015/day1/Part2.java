package jabre.adventofcode.year2015.day1;

import jabre.adventofcode.Solution;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day1FileContents fileContents = Part1.getFileContents();
        int floor = 0;
        int i = 0;
        for(; i < fileContents.parentheses().length(); i++) {
            floor += fileContents.parentheses().charAt(i) == ')' ? -1: 1;
            if(floor == -1) break;
        }
        return String.valueOf(i + 1);
    }
}
