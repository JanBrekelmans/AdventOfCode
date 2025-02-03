package jabre.adventofcode.year2024.day1;

import jabre.adventofcode.Solution;

import java.util.Map;
import java.util.stream.Collectors;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }
    @Override
    public String solve() {
        Part1.Day1FileContents fileContents = Part1.getFileContents();
        Map<Integer, Long> occurenceMap = fileContents.secondColumn().stream()
                .collect(Collectors.groupingBy(a -> a, Collectors.counting()));
        long result = fileContents.firstColumn().stream()
                .mapToLong(i -> i*occurenceMap.getOrDefault(i, 0L))
                .sum();
        return String.valueOf(result);
    }
}
