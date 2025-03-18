package jabre.adventofcode.year2015.day13;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.IntBox;
import jabre.adventofcode.util.Permutations;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day13FileContents fileContents = getFileContents();
        Map<String, Map<String, Integer>> effectMap = new HashMap<>();
        for (SeatingResult seating : fileContents.seatingResults()) {
            Map<String, Integer> effects = effectMap.computeIfAbsent(seating.person, k -> new HashMap<>());
            effects.put(seating.neighbour(), seating.effect());
        }

        IntBox bestResult = new IntBox(Integer.MIN_VALUE);
        Consumer<List<String>> updateBestResult = l -> {
            int totalHappiness = 0;
            for (int i = 0; i < l.size(); i++) {
                String person = l.get(i);
                String neighbour = l.get((i + 1) % l.size());

                int effect = effectMap.get(person).get(neighbour) + effectMap.get(neighbour).get(person);
                totalHappiness += effect;
            }
            bestResult.element = Math.max(bestResult.element, totalHappiness);
        };
        Permutations.heapsAlgorithm(effectMap.keySet().stream().toList(), updateBestResult);


        return String.valueOf(bestResult.element);
    }

    static Day13FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day13.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath());
             Stream<String> lines = reader.lines()) {
            Pattern loves = Pattern.compile("(\\D*) would gain (\\d*) happiness units by sitting next to (\\D*).");
            Pattern hates = Pattern.compile("(\\D*) would lose (\\d*) happiness units by sitting next to (\\D*).");
            return new Day13FileContents(lines.map(l -> {
                        Matcher matcher = loves.matcher(l);
                        if (matcher.find()) {
                            String person = matcher.group(1);
                            String neighbour = matcher.group(3);
                            int result = Integer.parseInt(matcher.group(2));
                            return new SeatingResult(person, neighbour, result);
                        }

                        matcher = hates.matcher(l);
                        if (matcher.find()) {
                            String person = matcher.group(1);
                            String neighbour = matcher.group(3);
                            int result = -Integer.parseInt(matcher.group(2));
                            return new SeatingResult(person, neighbour, result);
                        }
                        throw new RuntimeException("Could not match line " + l);
                    })
                    .toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record SeatingResult(String person, String neighbour, int effect) {
    }

    record Day13FileContents(List<SeatingResult> seatingResults) {
    }
}
