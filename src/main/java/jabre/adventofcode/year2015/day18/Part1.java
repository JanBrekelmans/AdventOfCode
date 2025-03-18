package jabre.adventofcode.year2015.day18;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.Box;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day18FileContents fileContents = getFileContents();
        NewStateComputer stateComputer = (grid, x, y) -> {
            int totalHash = 0;
            for(int i = Math.max(0, x-1); i < Math.min(grid.length, x+2); i++){
                for(int j = Math.max(0, y-1); j < Math.min(grid[0].length, y+2); j++) {
                    if(i == x && j == y) continue;
                    if(grid[i][j] == '#') totalHash++;
                }
            }

            if(grid[x][y] == '#' && (totalHash == 2 || totalHash == 3)) {
                return '#';
            } else if (grid[x][y] == '.' && totalHash == 3) {
                return '#';
            }
            return '.';
        };

        char[][] endGrid = compute(fileContents.grid, stateComputer, 100);

        return String.valueOf(numberOfOnLights(endGrid));
    }

    static char[][] compute(char[][] initialGrid, NewStateComputer stateComputer, int numIterations) {
        Box<char[][]> oldGrid = new Box<>(initialGrid);
        Box<char[][]> currentGrid = new Box<>(new char[initialGrid.length][initialGrid[0].length]);

        for(int iter = 0; iter < numIterations; iter++) {
            char[][] old = oldGrid.element;
            char[][] current = currentGrid.element;

            for(int i = 0; i < old.length; i++) {
                for(int j = 0; j < old[0].length; j++) {
                    current[i][j] = stateComputer.newState(old, i, j);
                }
            }

            Box.swap(oldGrid, currentGrid);
        }

        return oldGrid.element;
    }

    static int numberOfOnLights(char[][] grid) {
        int total = 0;
        for(char[] line: grid) {
            for (char c: line) {
                if(c == '#') total++;
            }
        }
        return total;
    }

    static Day18FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day18.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath());
             Stream<String> lines = reader.lines()) {
            char[][] grid = lines.map(String::toCharArray).toArray(char[][]::new);
            return new Day18FileContents(grid);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record Day18FileContents(char[][] grid) {
    }

    interface NewStateComputer {
        char newState(char[][] grid, int x, int y);
    }
}
