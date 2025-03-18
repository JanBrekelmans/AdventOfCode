package jabre.adventofcode.year2015.day19;

import jabre.adventofcode.Solution;

import java.util.*;

import static jabre.adventofcode.year2015.day19.Part1.generatedMolecules;
import static jabre.adventofcode.year2015.day19.Part1.getFileContents;

public class Part2 implements Solution{
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day19FileContents fileContents = getFileContents();
        String goal = "e";
        Map<String, List<String>> newReplacementPatterns = createReverseReplacementPatterns(fileContents.replacePatterns());


        Set<String> current = Set.of(fileContents.initialMolecule());
        Set<String> seen = new HashSet<>();
        int index = 0;
        while(!current.contains(goal)) {
            index++;
            Set<String> newlyGenerated = new HashSet<>();

            for(String molecule: current) {
                if(seen.contains(molecule)) continue;
                newlyGenerated.addAll(generatedMolecules(molecule, fileContents.replacePatterns()));
            }
            seen.addAll(current);
            current = newlyGenerated;
        }

        return String.valueOf(index);
    }

    static Map<String, List<String>> createReverseReplacementPatterns(Map<String, List<String>> replacementPatterns) {
        Map<String, List<String>> newReplacementPatterns = new HashMap<>();
        for(Map.Entry<String, List<String>> es: replacementPatterns.entrySet()) {
            String molecule = es.getKey();
            List<String> replacements = es.getValue();
            for(String replacement: replacements) {
                newReplacementPatterns.computeIfAbsent(replacement, k -> new ArrayList<>()).add(molecule);
            }
        }

        return newReplacementPatterns;
    }
}
