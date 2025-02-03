package jabre.adventofcode.year2024.day1;

import jabre.adventofcode.Solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day1FileContents fileContents = getFileContents();
        fileContents.firstColumn.sort(Integer::compare);
        fileContents.secondColumn.sort(Integer::compare);

        int result = 0;
        for (int i = 0; i < fileContents.firstColumn.size(); i++) {
            int i1 = fileContents.firstColumn.get(i);
            int i2 = fileContents.secondColumn.get(i);
            result += Math.abs(i1 - i2);
        }


        return String.valueOf(result);
    }

    static Day1FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day1.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath());
             Stream<String> lines = reader.lines()) {
            ArrayList<Integer> firstColumn = new ArrayList<>();
            ArrayList<Integer> secondColumn = new ArrayList<>();
                lines.forEach(line -> {
                    String[] split = line.split("( ) *");
                    firstColumn.add(Integer.parseInt(split[0]));
                    secondColumn.add(Integer.parseInt(split[1]));
                });
                return new Day1FileContents(firstColumn, secondColumn);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    record Day1FileContents(ArrayList<Integer> firstColumn, ArrayList<Integer> secondColumn) {
    }
}
