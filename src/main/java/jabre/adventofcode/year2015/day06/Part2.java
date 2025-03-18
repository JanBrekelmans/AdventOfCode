package jabre.adventofcode.year2015.day06;

import jabre.adventofcode.Solution;

import static jabre.adventofcode.year2015.day06.Part1.getFileContents;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day6FileContents fileContents = getFileContents();
        int[][] grid = new int[1000][1000];
        for(Part1.Instruction instruction: fileContents.instructions()) {
            for(int x = instruction.lowerLeft().x(); x <= instruction.upperRight().x(); x++) {
                for(int y = instruction.lowerLeft().y(); y <= instruction.upperRight().y(); y++) {
                    int old = grid[x][y];
                    grid[x][y] = switch (instruction.instructionType()) {
                        case TURN_ON -> old+1;
                        case TURN_OFF -> Math.max(0, old-1);
                        case TOGGLE -> old+2;
                    };
                }
            }
        }

        return String.valueOf(determineBrightness(grid));
    }

    static long determineBrightness(int[][] grid) {
        long total = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                total += grid[i][j];
            }
        }
        return total;
    }
}
