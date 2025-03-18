package jabre.adventofcode.year2015.day12;

import jabre.adventofcode.Solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        var fileContents = getFileContents();
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(fileContents.json);
        int total = 0;
        while (m.find()) {
            total += Integer.parseInt(m.group());
        }
        return String.valueOf(total);
    }

    static Day12FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day12.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath());
             Stream<String> lines = reader.lines()) {
            return new Day12FileContents(lines.findFirst().get());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record Day12FileContents(String json) {
    }
}
