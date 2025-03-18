package jabre.adventofcode.year2018.day01;

import jabre.adventofcode.Solution;

import java.util.HashSet;
import java.util.Set;

import static jabre.adventofcode.year2018.day01.Part1.getFileContents;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }
    @Override
    public String solve() {
        Part1.Day1FileContents fileContents = getFileContents();

        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        int sum = 0;
        for(int i = 0;; i++) {
            int e = fileContents.values().get(i % fileContents.values().size());
            sum += e;
            if(seen.contains(sum)) return String.valueOf(sum);
            seen.add(sum);
        }
    }
}
