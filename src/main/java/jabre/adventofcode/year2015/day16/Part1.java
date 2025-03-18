package jabre.adventofcode.year2015.day16;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day16FileContents fileContents = getFileContents();
        Map<String, Integer> specificSueMap = fileContents.specificSueProperties().properties();
        for(SueProperties sue: fileContents.sues()) {
            if(specificSueMap.containsKey(sue.p1())) {
                if(!specificSueMap.get(sue.p1).equals(sue.amount1)) {
                    continue;
                }
            }
            if(specificSueMap.containsKey(sue.p2())) {
                if(!specificSueMap.get(sue.p2).equals(sue.amount2)) {
                    continue;
                }
            }
            if(specificSueMap.containsKey(sue.p3())) {
                if(!specificSueMap.get(sue.p3).equals(sue.amount3)) {
                    continue;
                }
            }
            return String.valueOf(sue.id);
        }
        throw new RuntimeException("Could not identify SUE");
    }

    static Day16FileContents getFileContents() {
        URL allSuePropertiesFileName = Part1.class.getResource("sue_properties.txt");
        URL specificSuePropertiesFileName = Part1.class.getResource("specific_sue.txt");
        try (BufferedReader allSuePropertiesReader = Files.newBufferedReader(new File(allSuePropertiesFileName.toURI()).toPath());
             Stream<String> allSuePropertiesLines = allSuePropertiesReader.lines();
             BufferedReader specificSuePropertiesReader = Files.newBufferedReader(new File(specificSuePropertiesFileName.toURI()).toPath());
             Stream<String> specificSuePropertiesLines = specificSuePropertiesReader.lines()) {
            final Pattern allSuePropertiesPattern = Pattern.compile("Sue (\\d*): (\\D*): (\\d*), (\\D*): (\\d*), (\\D*): (\\d*)");
            List<SueProperties> allSueProperties = allSuePropertiesLines.map(line -> {
                Matcher matcher = allSuePropertiesPattern.matcher(line);
                if (matcher.find()) {
                    int id = Integer.parseInt(matcher.group(1));
                    String p1 = matcher.group(2);
                    int amount1 = Integer.parseInt(matcher.group(3));
                    String p2 = matcher.group(4);
                    int amount2 = Integer.parseInt(matcher.group(5));
                    String p3 = matcher.group(6);
                    int amount3 = Integer.parseInt(matcher.group(7));
                    return new SueProperties(id, p1, amount1, p2, amount2, p3, amount3);
                }

                throw new RuntimeException("Could not match line " + line);
            }).toList();

            final Pattern specificSuePattern = Pattern.compile("(\\D*): (\\d*)");
            Map<String, Integer> specificSuePropertiesMap = specificSuePropertiesLines.map(line -> {
                Matcher matcher = specificSuePattern.matcher(line);
                if(matcher.find()) {
                    String p = matcher.group(1);
                    int amount = Integer.parseInt(matcher.group(2));
                    return new Pair<>(p, amount);
                }
                throw new RuntimeException("Could not match line " + line);
            }).collect(Collectors.toMap(Pair::car, Pair::cdr));

            return new Day16FileContents(allSueProperties, new SpecificSueProperties(specificSuePropertiesMap));

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record SueProperties(int id, String p1, int amount1, String p2, int amount2, String p3, int amount3) {
    }

    record SpecificSueProperties(Map<String, Integer> properties) {
    }

    record Day16FileContents(List<SueProperties> sues, SpecificSueProperties specificSueProperties) {
    }
}
