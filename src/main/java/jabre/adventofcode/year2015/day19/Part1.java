package jabre.adventofcode.year2015.day19;

import jabre.adventofcode.Solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day19FileContents fileContents = getFileContents();
        return String.valueOf(generatedMolecules(fileContents.initialMolecule(), fileContents.replacePatterns()).size());
    }

    static Set<String> generatedMolecules(String molecule, Map<String, List<String>> replacePatterns) {
        Set<String> generated = new HashSet<>();

        final BiPredicate<String, Integer> canReplace = (pattern, index) -> {
            if(pattern.length() + index > molecule.length()) return false;

            if(molecule.substring(index, index+ pattern.length()).equals(pattern)) return true;
            return false;
        };

        for(int i = 0; i < molecule.length(); i++) {
            for(Map.Entry<String, List<String>> es: replacePatterns.entrySet()) {
                String pattern = es.getKey();
                if(canReplace.test(pattern, i)) {
                    for(String replacement: es.getValue()) {
                        StringBuilder builder = new StringBuilder();
                        builder.append(molecule.substring(0, i));
                        builder.append(replacement);
                        builder.append(molecule.substring(i + pattern.length()));
                        generated.add(builder.toString());
                    }
                }
            }
        }


        return generated;
    }

    static Day19FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day19.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath());
             Stream<String> lines = reader.lines()) {
            Pattern pattern = Pattern.compile("(\\D*) => (\\D*)");
            Map<String, List<String>> replacePatterns = new HashMap<>();
            String initialMolecule = null;
            for(Iterator<String> iterator = lines.iterator(); iterator.hasNext();) {
                String line = iterator.next();
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    String molecule = matcher.group(1);
                    String replacement = matcher.group(2);

                    replacePatterns.computeIfAbsent(molecule, k -> new ArrayList<>()).add(replacement);
                } else if (!line.isBlank()) {
                    initialMolecule = line;
                }
            }

            return new Day19FileContents(replacePatterns, initialMolecule);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record Day19FileContents(Map<String, List<String>> replacePatterns, String initialMolecule) {
    }
}
