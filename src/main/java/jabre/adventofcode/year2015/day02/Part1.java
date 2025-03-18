package jabre.adventofcode.year2015.day02;

import jabre.adventofcode.Solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day2FileContents fileContents = getFileContents();
        int areaNeeded = 0;
        for(BoxDimensions dimensions: fileContents.boxDimensions()) {
            areaNeeded += dimensions.getAreaNeededIncludingSlack();
        }
        return String.valueOf(areaNeeded);
    }

    static Day2FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day2.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath());
             Stream<String> lines = reader.lines()) {
            return new Day2FileContents(lines.map(line -> {
                String[] split = line.split("x");
                return new BoxDimensions(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]));
            }).toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record BoxDimensions(int length, int width, int height) {
        private int getSmallestSide() {
            return Math.min(length*width, Math.min(width*height, height*length));
        }

        int getAreaNeededIncludingSlack() {
            return 2*(length * width + width * height + height * length) + getSmallestSide();
        }

        private int getVolume() {
            return length * width * height;
        }
        int getLengthOfBowNeeded() {
            return 2*(length + width + height - Math.max(length, Math.max(width, height))) + getVolume();
        }
    }

    record Day2FileContents(List<BoxDimensions> boxDimensions) {
    }
}
