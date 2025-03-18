package jabre.adventofcode.year2015.day03;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.Coordinate2D;
import jabre.adventofcode.util.IntBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day3FileContents fileContents = getFileContents();
        Map<Coordinate2D, IntBox> numberOfPresentsReceived = new HashMap<>();
        int x = 0, y = 0;
        numberOfPresentsReceived.computeIfAbsent(new Coordinate2D(x,y), k -> new IntBox()).element++;
        for(char c: fileContents.path().toCharArray()) {
            x += dx(c);
            y += dy(c);
            numberOfPresentsReceived.computeIfAbsent(new Coordinate2D(x,y), k -> new IntBox()).element++;
        }
        return String.valueOf(numberOfPresentsReceived.size());
    }

    static int dx(char c) {
        return switch (c) {
            case '<' -> -1;
            case '>' -> 1;
            case 'v','^' -> 0;
            default -> throw new RuntimeException("Did not recognize direction " + c);
        };
    }

    static int dy(char c) {
        return switch (c) {
            case 'v' -> -1;
            case '^' -> 1;
            case '<','>' -> 0;
            default -> throw new RuntimeException("Did not recognize direction " + c);
        };
    }

    static Day3FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day3.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath());
             Stream<String> lines = reader.lines()) {
            return new Day3FileContents(lines.findFirst().get());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record Day3FileContents(String path) {}
}
