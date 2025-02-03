package jabre.adventofcode.year2015.day5;

import jabre.adventofcode.Solution;

import static jabre.adventofcode.year2015.day5.Part1.getFileContents;

public class Part2 implements Solution {
    final Part1.Rule doublePairRule = (String line) -> {
        for(int i = 0; i < line.length() - 3; i++) {
            char c1 = line.charAt(i);
            char c2 = line.charAt(i+1);
            for(int j = i + 2; j < line.length()-1; j++) {
                char c3 = line.charAt(j);
                char c4 = line.charAt(j+1);
                if(c1 == c3 && c2 == c4) return true;
            }
        }
        return false;
    };
    final Part1.Rule repetitionRule = (String line) -> {
        for(int i = 0; i < line.length() - 2; i++) {
            char c1 = line.charAt(i);
            char c2 = line.charAt(i + 2);
            if(c1 == c2) return true;
        }
        return false;
    };
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day5FileContents fileContents = getFileContents();
        long numberOfNiceLines = fileContents.lines().stream().filter(line -> doublePairRule.satisfies(line) && repetitionRule.satisfies(line)).count();
        return String.valueOf(numberOfNiceLines);
    }
}
