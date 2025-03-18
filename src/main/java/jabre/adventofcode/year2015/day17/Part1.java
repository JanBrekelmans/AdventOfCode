package jabre.adventofcode.year2015.day17;

import jabre.adventofcode.Solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day17FileContents fileContents = getFileContents();
        return String.valueOf(dp(fileContents.containerSizes(), 0, 0, 150, new ArrayDeque<>(), s -> {}));
    }

    static int dp(List<Integer> amounts, int currentIndex, int currentTotal, int target, Deque<Integer> currentAmounts, Consumer<Deque<Integer>> onTargetReach) {
        if(currentTotal > target) return 0;
        if(currentTotal == target){
            onTargetReach.accept(currentAmounts);
            return 1;
        }
        if(currentIndex == amounts.size()) return 0;

        int p1 = dp(amounts, currentIndex+1, currentTotal, target, currentAmounts, onTargetReach);
        currentAmounts.add(amounts.get(currentIndex));
        int p2 = dp(amounts, currentIndex+1, currentTotal+amounts.get(currentIndex), target, currentAmounts, onTargetReach);
        currentAmounts.removeLast();
        return p1+p2;
    }

    static Day17FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day17.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath());
             Stream<String> lines = reader.lines()) {
            return new Day17FileContents(lines.map(Integer::parseInt).toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record Day17FileContents(List<Integer> containerSizes) {
    }
}
