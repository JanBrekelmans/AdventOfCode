package jabre.adventofcode.year2015.day17;

import jabre.adventofcode.Solution;

import java.util.*;
import java.util.function.Consumer;

import static jabre.adventofcode.year2015.day17.Part1.dp;
import static jabre.adventofcode.year2015.day17.Part1.getFileContents;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day17FileContents fileContents = getFileContents();
        Map<Integer, Integer> numberOfDifferentWaysForAmountOfContainers = new HashMap<>();

        Consumer<Deque<Integer>> onTargetConsumer = deque -> {
            numberOfDifferentWaysForAmountOfContainers.putIfAbsent(deque.size(), 0);
            numberOfDifferentWaysForAmountOfContainers.compute(deque.size(), (k,v) -> v+1);
        };

        dp(fileContents.containerSizes(), 0, 0, 150, new ArrayDeque<>(), onTargetConsumer);

        int numberOfDifferentWaysAtMinimum = numberOfDifferentWaysForAmountOfContainers.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getKey)).get().getValue();
        return String.valueOf(numberOfDifferentWaysAtMinimum);
    }
}
