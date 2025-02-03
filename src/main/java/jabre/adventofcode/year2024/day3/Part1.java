package jabre.adventofcode.year2024.day3;

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
    private final static String regex = "mul\\((\\d+),(\\d+)\\)";
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day3FileContents contents = getFileContents();
        return String.valueOf(getMultiplicationResults(contents.lines));
    }

    static Day3FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day3.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath())) {
            return new Day3FileContents(reader.lines().toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    static int getMultiplicationResults(List<String> lines) {
        int result = 0;
        Pattern pattern = Pattern.compile(regex);
        for(String line: lines) {
            Matcher matcher = pattern.matcher(line);

            while(matcher.find()) {
                result += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            }
        }
        return result;
    }

    record Day3FileContents(List<String> lines) {}
}
