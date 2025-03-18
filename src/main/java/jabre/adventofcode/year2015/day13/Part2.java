package jabre.adventofcode.year2015.day13;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.IntBox;
import jabre.adventofcode.util.Permutations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static jabre.adventofcode.year2015.day13.Part1.getFileContents;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day13FileContents fileContents = getFileContents();
        Map<String, Map<String, Integer>> effectMap = new HashMap<>();
        for (Part1.SeatingResult seating : fileContents.seatingResults()) {
            Map<String, Integer> effects = effectMap.computeIfAbsent(seating.person(), k -> new HashMap<>());
            effects.put(seating.neighbour(), seating.effect());
        }

        IntBox bestResult = new IntBox(Integer.MIN_VALUE);
        Consumer<List<String>> updateBestResult = l -> {
            int totalHappiness = 0;
            for (int i = 0; i < l.size(); i++) {
                String person = l.get(i);
                String neighbour = l.get((i + 1) % l.size());

                if(!person.equals("ME") && !neighbour.equals("ME")) {
                    int effect = effectMap.get(person).get(neighbour) + effectMap.get(neighbour).get(person);
                    totalHappiness += effect;
                }
            }
            bestResult.element = Math.max(bestResult.element, totalHappiness);
        };
        List<String> names = Stream.concat(Stream.of("ME"), effectMap.keySet().stream()).toList();
        Permutations.heapsAlgorithm(names, updateBestResult);


        return String.valueOf(bestResult.element);
    }
}
