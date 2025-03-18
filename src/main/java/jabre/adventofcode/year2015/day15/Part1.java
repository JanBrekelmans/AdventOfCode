package jabre.adventofcode.year2015.day15;

import jabre.adventofcode.Solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Part1 implements Solution {

    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day15FileContents fileContents = getFileContents();

        int best = 0;
        for(int i = 0; i <= 100; i++) {
            for(int j = 0; j <= 100 - i;j++) {
                for(int k = 0; k <= 100 - i - j; k++) {
                    int l = 100 - i - j - k;
                    if(l < 0) continue;
                    best = Math.max(best, determineScore(List.of(i,j,k,l), fileContents.ingredients()));
                }
            }
        }

        return String.valueOf(best);
    }

    static Function<Function<IngredientProperties, Integer>, Integer> determineAmountOfTypeProvider(List<Integer> amounts, List<IngredientProperties> ingredients) {
        return (fun) -> {
            int total = 0;
            for(int i = 0; i < amounts.size(); i++) {
                total += amounts.get(i) * fun.apply(ingredients.get(i));
            }
            return total;
        };
    }

    static int determineScore(List<Integer> amounts, List<IngredientProperties> ingredients) {
        final Function<Function<IngredientProperties, Integer>, Integer> determineAmountOfType = determineAmountOfTypeProvider(amounts, ingredients);

        int score = 1;
        score *= Math.max(0, determineAmountOfType.apply(IngredientProperties::capacity));
        score *= Math.max(0, determineAmountOfType.apply(IngredientProperties::durability));
        score *= Math.max(0, determineAmountOfType.apply(IngredientProperties::flavor));
        score *= Math.max(0, determineAmountOfType.apply(IngredientProperties::texture));

        return score;
    }

    static Day15FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day15.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath());
             Stream<String> lines = reader.lines()) {
            Pattern ingredientPattern = Pattern.compile("(\\D*): capacity (-?\\d*), durability (-?\\d*), flavor (-?\\d*), texture (-?\\d*), calories (-?\\d*)");
            return new Day15FileContents(lines.map(l -> {
                        Matcher matcher = ingredientPattern.matcher(l);
                        if (matcher.find()) {
                            String name = matcher.group(1);
                            int capacity = Integer.parseInt(matcher.group(2));
                            int durability = Integer.parseInt(matcher.group(3));
                            int flavor = Integer.parseInt(matcher.group(4));
                            int texture = Integer.parseInt(matcher.group(5));
                            int calories = Integer.parseInt(matcher.group(6));
                            return new IngredientProperties(name, capacity, durability, flavor, texture, calories);
                        }
                        throw new RuntimeException("Could not match line " + l);
                    })
                    .toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record IngredientProperties(String name, int capacity, int durability, int flavor, int texture, int calories) {
    }

    record Day15FileContents(List<IngredientProperties> ingredients) {}
}
