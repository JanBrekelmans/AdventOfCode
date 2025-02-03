package jabre.adventofcode.year2015.day2;

import jabre.adventofcode.Solution;

import static jabre.adventofcode.year2015.day2.Part1.getFileContents;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }
    @Override
    public String solve() {
        Part1.Day2FileContents fileContents = getFileContents();
        int bowNeeded = 0;
        for(Part1.BoxDimensions dimensions: fileContents.boxDimensions()) {
            bowNeeded += dimensions.getLengthOfBowNeeded();
        }
        return String.valueOf(bowNeeded);
    }
}
