package jabre.adventofcode.year2015.day8;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.Coordinate2D;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }
    @Override
    public String solve() {
        var fileContents = getFileContents();
        return null;
    }

    static Day8FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day8.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath())) {
            List<Line> lines = new ArrayList<>();
            char[] buffer = new char[256];
            int length = 0;

            int x;
            while((x = reader.read()) != -1) {
                if (x == '\\') {
                    int next = reader.read();
                    if(next == 'n') {
                        // New line
                        char[] rawLine = Arrays.copyOf(buffer, length);
                        String line = new String(rawLine);
                        lines.add(new Line(line, rawLine));
                        length = 0;
                        continue;
                    }
                    buffer[length++] = '\\';
                    buffer[length++] = (char) next;
                } else {
                    buffer[length++] = (char) x;
                }
            }

            return new Day8FileContents(lines);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record Line(String line, char[] rawLine){}
    record Day8FileContents(List<Line> lines) {}
}
