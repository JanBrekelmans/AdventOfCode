package jabre.adventofcode.year2018.day02;

import jabre.adventofcode.Solution;

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
        int nAppearsTwice = 0, nAppearsThrice = 0;
        for (String id : fileContents.ids) {
            var properties = determineProperties(id);
            if (properties.letterAppearsTwice) nAppearsTwice++;
            if (properties.letterAppearsThrice) nAppearsThrice++;
        }
        return String.valueOf(nAppearsTwice * nAppearsThrice);
    }

    record IdProperties(boolean letterAppearsTwice, boolean letterAppearsThrice) {
    }

    IdProperties determineProperties(String id) {
        int[] counts = new int[26];
        for (char c : id.toCharArray()) {
            counts[c - 'a']++;
        }
        boolean appearsTwice = Arrays.stream(counts).anyMatch(i -> i == 2);
        boolean appearsThrice = Arrays.stream(counts).anyMatch(i -> i == 3);
        return new IdProperties(appearsTwice, appearsThrice);
    }

    static Day2FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day2.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath());
             Stream<String> lines = reader.lines()) {
            return new Day2FileContents(lines.toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record Day2FileContents(List<String> ids) {
    }
}
