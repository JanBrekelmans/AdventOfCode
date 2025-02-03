package jabre.adventofcode.year2024.day6;

import jabre.adventofcode.Solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        return null;
    }

    static Day6FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day6.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath());
             Stream<String> lines = reader.lines()) {
            List<List<Character>> grid = new ArrayList<>();
            lines.forEach(line -> {
                grid.add(line.chars().boxed().map(c -> (char) c.intValue()).collect(Collectors.toCollection(ArrayList::new)));
            });
            return new Day6FileContents(grid);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    record Day6FileContents(List<List<Character>> grid) {
    }
}
