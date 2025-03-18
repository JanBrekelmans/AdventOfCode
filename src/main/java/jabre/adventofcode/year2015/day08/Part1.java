package jabre.adventofcode.year2015.day08;

import jabre.adventofcode.Solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day8FileContents fileContents = getFileContents();
        return String.valueOf(fileContents.lines().stream()
                .mapToInt(Part1::differenceBetweenCodeAndMemoryLength)
                .sum());
    }

    static int differenceBetweenCodeAndMemoryLength(String s) {
        return s.length() - countNumOfMemorySymbols(s) + 2;
    }

    private static int countNumOfMemorySymbols(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\\') {
                if (i + 1 < s.length()) {
                    char n = s.charAt(i + 1);
                    if (n == '\\' || n == '"') {
                        i++;
                    } else if (n == 'x' && i + 3 < s.length()) {
                        i += 3;
                    }
                }
            }
            count++;
        }
        return count;
    }

    static Day8FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day8.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath())) {
            return new Day8FileContents(reader.lines().toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record Day8FileContents(List<String> lines) {
    }
}
