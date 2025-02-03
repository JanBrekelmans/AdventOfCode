package jabre.adventofcode.year2024.day5;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        var fileContents = getFileContents();
        return null;
    }
//
//    static boolean isValid(List<Integer> nums, KeyRules keyRules) {
//        for(int i = 0; i < nums.size(); i++) {
//            int num = nums.get(i);
//            var keyRuleMap = keyRules.keyRuleMap.get(num);
//            if(keyRuleMap == null) continue;
//
//            for(int j = 0; j < i; j++) {
//                int before = nums.get(j);
//            }
//            for(int j = i+1; j < nums.size(); j++) {
//
//            }
//        }
//    }

    static Pair<KeyRules, List<PageNumbering>> getFileContents() {
        URL fileName = Part1.class.getResource("day5.txt");

        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath())) {
            Map<Integer, KeyRule> keyRuleMap = new HashMap<>();
            List<PageNumbering> pageNumberings = new ArrayList<>();

            boolean inRulesPart = true;
            Iterator<String> iterator = reader.lines().iterator();
            while (iterator.hasNext()) {
                String line = iterator.next();

                if(line.isBlank()) {
                    inRulesPart = false;
                } else if (inRulesPart) {
                    String[] split = line.split("\\|");
                    int before = Integer.parseInt(split[0]);
                    int after = Integer.parseInt(split[1]);

                    keyRuleMap.computeIfAbsent(before, k -> new KeyRule(k, new HashSet<>(), new HashSet<>()))
                            .after.add(after);
                    keyRuleMap.computeIfAbsent(after, k -> new KeyRule(k, new HashSet<>(), new HashSet<>()))
                            .before.add(before);
                } else {
                    pageNumberings.add(new PageNumbering(Arrays.stream(line.split(","))
                            .map(Integer::parseInt).toList()));
                }
            }

            return new Pair<>(new KeyRules(keyRuleMap), pageNumberings);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record KeyRules(Map<Integer, KeyRule> keyRuleMap) {}
    record KeyRule(int key, Set<Integer> before, Set<Integer> after) {}
    record PageNumbering(List<Integer> pageNumbers){}
}
