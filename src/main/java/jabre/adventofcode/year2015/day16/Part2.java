package jabre.adventofcode.year2015.day16;

import jabre.adventofcode.Solution;

import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import static jabre.adventofcode.year2015.day16.Part1.getFileContents;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day16FileContents fileContents = getFileContents();
        Map<String, Integer> specificSueMap = fileContents.specificSueProperties().properties();
        final Function<String, BiPredicate<Integer, Integer>> satisfies = s -> {
            final BiPredicate<Integer, Integer> predicate =
                    switch (s) {
                        case "cats", "trees" -> (n1,n2) -> n1 < n2;
                        case "pomeranians", "goldfish" -> (n1,n2) -> n1 > n2;
                        default -> Integer::equals;
                    };
            return predicate;
        };

        for(Part1.SueProperties sue: fileContents.sues()) {
            if(specificSueMap.containsKey(sue.p1())) {
                int amountSpecificSue = specificSueMap.get(sue.p1());
                int thisSueAmount = sue.amount1();
                BiPredicate<Integer, Integer> predicate = satisfies.apply(sue.p1());
                if(!predicate.test(amountSpecificSue, thisSueAmount)) continue;
            }
            if(specificSueMap.containsKey(sue.p2())) {
                int amountSpecificSue = specificSueMap.get(sue.p2());
                int thisSueAmount = sue.amount2();
                BiPredicate<Integer, Integer> predicate = satisfies.apply(sue.p2());
                if(!predicate.test(amountSpecificSue, thisSueAmount)) continue;
            }
            if(specificSueMap.containsKey(sue.p3())) {
                int amountSpecificSue = specificSueMap.get(sue.p3());
                int thisSueAmount = sue.amount3();
                BiPredicate<Integer, Integer> predicate = satisfies.apply(sue.p3());
                if(!predicate.test(amountSpecificSue, thisSueAmount)) continue;
            }
            return String.valueOf(sue.id());
        }
        throw new RuntimeException("Could not identify SUE");
    }
}
