package jabre.adventofcode.year2015.day18;

import jabre.adventofcode.Solution;

import static jabre.adventofcode.year2015.day18.Part1.*;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day18FileContents fileContents = getFileContents();
        char[][] initialGrid = fileContents.grid();
        initialGrid[0][0] = '#';
        initialGrid[0][initialGrid[0].length - 1] = '#';
        initialGrid[initialGrid.length-1][0] = '#';
        initialGrid[initialGrid.length-1][initialGrid[0].length - 1] = '#';

        Part1.NewStateComputer stateComputer = (grid, x, y) -> {
            if((x == 0 || x == grid.length - 1) && (y == 0 || y == grid[0].length - 1)) return '#';

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

        char[][] endGrid = compute(fileContents.grid(), stateComputer, 100);

        return String.valueOf(numberOfOnLights(endGrid));
    }
}
