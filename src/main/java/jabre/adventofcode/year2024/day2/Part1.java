package jabre.adventofcode.year2024.day2;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.NumericUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day2FileContents fileContents = getFileContents();
        int totalSafe = 0;
        for(List<Integer> row: fileContents.rows) {
            if(isSafe(row)) totalSafe++;
        }
        return String.valueOf(totalSafe);
    }

    static Day2FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day2.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath());
             Stream<String> lines = reader.lines()) {
            List<List<Integer>> rows = lines.map(line -> {
                String[] split = line.split("( ) *");
                return Arrays.stream(split)
                        .map(Integer::parseInt)
                        .toList();
            }).toList();
            return new Day2FileContents(rows);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

static  boolean isSafe(List<Integer> nums) {
        return NumericUtil.isStrictlyMonotonic(nums) &&
                NumericUtil.getOffsets(nums).stream()
                        .allMatch(i -> Math.abs(i) <= 3);
}

    record Day2FileContents(List<List<Integer>> rows) {
    }
}
