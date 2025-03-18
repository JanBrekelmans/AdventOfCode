package jabre.adventofcode.year2015.day07;

import jabre.adventofcode.Solution;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import static jabre.adventofcode.year2015.day07.Part1.getFileContents;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day7FileContents fileContents = getFileContents();
        Deque<Part1.Instruction> instructions = new ArrayDeque<>(fileContents.instructions());
        Map<String, Integer> valuesMap = new HashMap<>();
        valuesMap.put("b", 46065);
        while (!instructions.isEmpty()) {
            Part1.Instruction instruction = instructions.poll();
            if(instruction.canCompute(valuesMap)) {
                String identifier = instruction.identifier();
                if(valuesMap.containsKey(identifier)) continue;
                int value = instruction.computeValue(valuesMap) & Part1.BIT_MASK;
                valuesMap.put(identifier, value);
            } else {
                instructions.add(instruction);
            }
        }
        return String.valueOf(valuesMap.get("a"));
    }
}
