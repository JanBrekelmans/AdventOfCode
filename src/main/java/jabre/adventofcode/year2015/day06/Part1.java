package jabre.adventofcode.year2015.day06;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.Coordinate2D;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day6FileContents fileContents = getFileContents();
        boolean[][] grid = new boolean[1000][1000];
        for(Instruction instruction: fileContents.instructions()) {
            for(int x = instruction.lowerLeft().x(); x <= instruction.upperRight().x(); x++) {
                for(int y = instruction.lowerLeft().y(); y <= instruction.upperRight().y(); y++) {
                    boolean old = grid[x][y];
                    grid[x][y] = switch (instruction.instructionType()) {
                        case TURN_ON -> true;
                        case TURN_OFF -> false;
                        case TOGGLE -> !old;
                    };
                }
            }
        }

        return String.valueOf(numberOfToggledLights(grid));
    }

    static int numberOfToggledLights(boolean[][] grid) {
        int total = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j]) total++;
            }
        }
        return total;
    }

    static Day6FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day6.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath())) {

            final Pattern turnOffPattern = Pattern.compile("turn off (\\d+),(\\d+) through (\\d+),(\\d+)");
            final Pattern turnOnPattern = Pattern.compile("turn on (\\d+),(\\d+) through (\\d+),(\\d+)");
            final Pattern togglePattern = Pattern.compile("toggle (\\d+),(\\d+) through (\\d+),(\\d+)");

            return new Day6FileContents(reader.lines()
                    .map(line -> {
                        Matcher matcher = turnOffPattern.matcher(line);
                        if (matcher.find()) {
                            return new Instruction(InstructionType.TURN_OFF, new Coordinate2D(matcher.group(1), matcher.group(2)), new Coordinate2D(matcher.group(3), matcher.group(4)));
                        }
                        matcher = turnOnPattern.matcher(line);
                        if (matcher.find()) {
                            return new Instruction(InstructionType.TURN_ON, new Coordinate2D(matcher.group(1), matcher.group(2)), new Coordinate2D(matcher.group(3), matcher.group(4)));
                        }
                        matcher = togglePattern.matcher(line);
                        if (matcher.find()) {
                            return new Instruction(InstructionType.TOGGLE, new Coordinate2D(matcher.group(1), matcher.group(2)), new Coordinate2D(matcher.group(3), matcher.group(4)));
                        }

                        throw new RuntimeException("Could not parse expression " + line);
                    })
                    .toList()
            );
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    enum InstructionType {
        TURN_ON,
        TURN_OFF,
        TOGGLE
    }

    record Instruction(InstructionType instructionType, Coordinate2D lowerLeft, Coordinate2D upperRight) {
    }

    record Day6FileContents(List<Instruction> instructions) {
    }
}
