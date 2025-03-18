package jabre.adventofcode.year2015.day15;

import jabre.adventofcode.Solution;

import java.util.List;

import static jabre.adventofcode.year2015.day15.Part1.*;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day15FileContents fileContents = getFileContents();

        int best = 0;
        for(int i = 0; i <= 100; i++) {
            for(int j = 0; j <= 100 - i;j++) {
                for(int k = 0; k <= 100 - i - j; k++) {
                    int l = 100 - i - j - k;
                    if(l < 0) continue;
                    int numCalories = determineAmountOfTypeProvider(List.of(i,j,k,l),fileContents.ingredients()).apply(IngredientProperties::calories);
                    if(numCalories != 500) continue;
                    best = Math.max(best, determineScore(List.of(i,j,k,l), fileContents.ingredients()));
                }
            }
        }

        return String.valueOf(best);
    }
}
