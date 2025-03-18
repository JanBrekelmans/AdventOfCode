package jabre.adventofcode.year2015.day10;

import jabre.adventofcode.Solution;

import static jabre.adventofcode.year2015.day10.Part1.determineNextIteration;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        String current = "3113322113";

        for(int i = 0; i < 50; i++) {
            current = determineNextIteration(current);
        }
        return String.valueOf(current.length());
    }
}
