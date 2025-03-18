package jabre.adventofcode.year2015.day04;

import jabre.adventofcode.Solution;

import static jabre.adventofcode.year2015.day04.Part1.determineKey;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        String key = "ckczppom";
        return String.valueOf(determineKey(key, 6));
    }
}
