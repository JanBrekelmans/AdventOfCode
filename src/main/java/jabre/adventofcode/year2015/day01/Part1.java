package jabre.adventofcode.year2015.day01;

import jabre.adventofcode.Solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.stream.Stream;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day1FileContents fileContents = getFileContents();
        return String.valueOf(determineFloorLevel(fileContents.parentheses));
    }

    static int determineFloorLevel(String parentheses) {
        return parentheses.chars().map(c -> c == ')'? -1:1).sum();
    }
    static Day1FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day1.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath());
             Stream<String> lines = reader.lines()) {
            return new Day1FileContents(lines.findFirst().get());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record Day1FileContents(String parentheses) {
    }

}
