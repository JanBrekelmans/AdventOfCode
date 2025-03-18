package jabre.adventofcode.year2015.day08;

import jabre.adventofcode.Solution;

import static jabre.adventofcode.year2015.day08.Part1.getFileContents;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day8FileContents fileContents = getFileContents();
        return String.valueOf(fileContents.lines().stream()
                .mapToInt(Part2::differenceBetweenExtraEncodedAndOriginalLength)
                .sum());
    }

    private static int differenceBetweenExtraEncodedAndOriginalLength(String s) {
        return lengthOfEncodedString(s) - s.length() + 2;
    }

    private static int lengthOfEncodedString(String s) {
        int count = 0;
        for(char c: s.toCharArray()) {
            if(c == '\\' || c == '"'){
                count+=2;
            } else{
                count++;
            }
        }
        return count;
    }
}
