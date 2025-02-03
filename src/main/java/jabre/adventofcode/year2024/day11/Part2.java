package jabre.adventofcode.year2024.day11;

import jabre.adventofcode.Solution;

import java.util.List;

import static jabre.adventofcode.year2024.day11.Part1.getFileContents;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }
    @Override
    public String solve() {
        Part1.Day11FileContents contents = getFileContents();
        List<Integer> current = contents.numbers();
        return String.valueOf(new Part1().determineTotalEndingStones(current, 75));
    }
}
