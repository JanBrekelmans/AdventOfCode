package jabre.adventofcode.year2015.day5;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.CharUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

public class Part1 implements Solution {
    final Rule vowelRule = (String line) -> line.chars().filter(CharUtil::isVowel).count() >= 3;
    final Rule twiceInARowRule = (String line) -> {
        for(int i = 0; i < line.length() - 1; i++) {
            if(line.charAt(i) == line.charAt(i+1)) return true;
        }
        return false;
    };
    final Rule forbiddenPairRule = (String line) -> {
        for(int i = 0; i < line.length() - 1; i++) {
            char c1 = line.charAt(i);
            char c2 = line.charAt(i+1);
            if(c1 == 'a' && c2 == 'b') return true;
            if(c1 == 'c' && c2 == 'd') return true;
            if(c1 == 'p' && c2 == 'q') return true;
            if(c1 == 'x' && c2 == 'y') return true;
        }
        return false;
    };
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }
    @Override
    public String solve() {
        Day5FileContents fileContents = getFileContents();
        long numberOfNiceLines = fileContents.lines().stream().filter(line -> vowelRule.satisfies(line) && twiceInARowRule.satisfies(line) && !forbiddenPairRule.satisfies(line)).count();
        return String.valueOf(numberOfNiceLines);
    }

    static Day5FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day5.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath());
             Stream<String> lines = reader.lines()) {
            return new Day5FileContents(lines.toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record Day5FileContents(List<String> lines) {}

    interface Rule{
        boolean satisfies(String line);
    }


}
