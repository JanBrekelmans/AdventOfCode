package jabre.adventofcode.year2015.day07;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.NumericUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 implements Solution {
    static final int BIT_MASK = 0xffff;
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }
    @Override
    public String solve() {
        Day7FileContents fileContents = getFileContents();
        Deque<Instruction> instructions = new ArrayDeque<>(fileContents.instructions);
        Map<String, Integer> valuesMap = new HashMap<>();
        while (!instructions.isEmpty()) {
            Instruction instruction = instructions.poll();
            if(instruction.canCompute(valuesMap)) {
                String identifier = instruction.identifier();
                int value = instruction.computeValue(valuesMap) & BIT_MASK;
                valuesMap.put(identifier, value);
            } else {
                instructions.add(instruction);
            }
        }
        return String.valueOf(valuesMap.get("a"));
    }

    static Day7FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day7.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath())) {
            final Pattern numberAssignmentPattern = Pattern.compile("^(\\d*) -> (\\S*)$");
            final Pattern variableAssignmentPattern = Pattern.compile("^(\\S*) -> (\\S*)$");
            final Pattern notPattern = Pattern.compile("NOT (\\S*) -> (\\S*)$");
            final Pattern leftShiftPattern = Pattern.compile("^(\\S*) LSHIFT (\\d*) -> (\\S*)$");
            final Pattern rightShiftPattern = Pattern.compile("^(\\S*) RSHIFT (\\d*) -> (\\S*)$");
            final Pattern orPattern = Pattern.compile("^(\\S*) OR (\\S*) -> (\\S*)$");
            final Pattern andPattern = Pattern.compile("^(\\S*) AND (\\S*) -> (\\S*)$");

            return new Day7FileContents(
                    reader.lines()
                            .map(line -> {
                                Matcher matcher = numberAssignmentPattern.matcher(line);
                                if (matcher.find()) {
                                    int amount = Integer.parseInt(matcher.group(1));
                                    String identifier = matcher.group(2);
                                    return new Assignment(identifier, amount);
                                }

                                matcher = variableAssignmentPattern.matcher(line);
                                if (matcher.find()) {
                                    String argument = matcher.group(1);
                                    String identifier = matcher.group(2);
                                    return new UnaryOperation(l -> l, identifier, argument);
                                }

                                matcher = rightShiftPattern.matcher(line);
                                if (matcher.find()) {
                                    String argument = matcher.group(1);
                                    int shiftAmount = Integer.parseInt(matcher.group(2));
                                    String identifier = matcher.group(3);
                                    return new UnaryOperation(l -> l >> shiftAmount, identifier, argument);
                                }

                                matcher = leftShiftPattern.matcher(line);
                                if (matcher.find()) {
                                    String argument = matcher.group(1);
                                    int shiftAmount = Integer.parseInt(matcher.group(2));
                                    String identifier = matcher.group(3);
                                    return new UnaryOperation(l -> l << shiftAmount, identifier, argument);
                                }

                                matcher = orPattern.matcher(line);
                                if (matcher.find()) {
                                    String argument1 = matcher.group(1);
                                    String argument2 = matcher.group(2);
                                    String identifier = matcher.group(3);
                                    if(NumericUtil.isNumber(argument1)) {
                                        return new UnaryOperation(l -> l | Integer.parseInt(argument1), identifier, argument2);
                                    } else if (NumericUtil.isNumber(argument2)) {
                                        return new UnaryOperation(l -> l | Integer.parseInt(argument2), identifier, argument1);
                                    }

                                    return new BinaryOperation((l1,l2) -> l1 | l2, identifier, argument1, argument2);
                                }

                                matcher = andPattern.matcher(line);
                                if (matcher.find()) {
                                    String argument1 = matcher.group(1);
                                    String argument2 = matcher.group(2);
                                    String identifier = matcher.group(3);
                                    if(NumericUtil.isNumber(argument1)) {
                                        return new UnaryOperation(l -> l & Integer.parseInt(argument1), identifier, argument2);
                                    } else if (NumericUtil.isNumber(argument2)) {
                                        return new UnaryOperation(l -> l & Integer.parseInt(argument2), identifier, argument1);
                                    }

                                    return new BinaryOperation((l1,l2) -> l1 & l2, identifier, argument1, argument2);
                                }

                                matcher = notPattern.matcher(line);
                                if (matcher.find()) {
                                    String argument = matcher.group(1);
                                    String identifier = matcher.group(2);
                                    return new UnaryOperation(l -> ~l, identifier, argument);
                                }

                                throw new RuntimeException("Could not parse expression " + line);
                            })
                            .toList()

            );
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record Day7FileContents(List<Instruction> instructions) {}

    interface Instruction {
        String identifier();
        boolean canCompute(Map<String, Integer> valueMap);
        int computeValue(Map<String, Integer> valueMap);
    }

    static class Assignment implements Instruction {
        private final String identifier;
        private final int value;

        Assignment(String identifier, int value) {
            this.identifier = identifier;
            this.value = value;
        }

        @Override
        public String identifier() {
            return identifier;
        }

        @Override
        public boolean canCompute(Map<String, Integer> valueMap) {
            return true;
        }

        @Override
        public int computeValue(Map<String, Integer> valueMap) {
            return value;
        }

        @Override
        public String toString() {
            return value + " -> " + identifier;
        }
    }

    static class UnaryOperation implements Instruction {
        private final String identifier, argument;
        private final Function<Integer, Integer> operator;

        UnaryOperation(Function<Integer, Integer> operator, String identifier, String argument) {
            this.identifier = identifier;
            this.argument = argument;
            this.operator = operator;
        }

        @Override
        public String identifier() {
            return identifier;
        }

        @Override
        public boolean canCompute(Map<String, Integer> valueMap) {
            return valueMap.containsKey(argument);
        }

        @Override
        public int computeValue(Map<String, Integer> valueMap) {
            return operator.apply(valueMap.get(argument));
        }

        @Override
        public String toString() {
            return argument + " -> " + identifier;
        }
    }

    static class BinaryOperation implements Instruction {

        private final String identifier, argument1, argument2;
        private final BiFunction<Integer,Integer, Integer> operator;

        BinaryOperation(BiFunction<Integer, Integer, Integer> operator, String identifier, String argument1, String argument2) {
            this.identifier = identifier;
            this.argument1 = argument1;
            this.argument2 = argument2;
            this.operator = operator;
        }

        @Override
        public String identifier() {
            return identifier;
        }

        @Override
        public boolean canCompute(Map<String, Integer> valueMap) {
            return valueMap.containsKey(argument1) && valueMap.containsKey(argument2);
        }

        @Override
        public int computeValue(Map<String, Integer> valueMap) {
            return operator.apply(valueMap.get(argument1), valueMap.get(argument2));
        }

        @Override
        public String toString() {
            return argument1 + " o " + argument2 + " -> " + identifier;
        }
    }
}
