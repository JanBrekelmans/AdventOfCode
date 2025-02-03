package jabre.adventofcode.year2024.day2;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.NumericUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jabre.adventofcode.year2024.day2.Part1.getFileContents;
import static jabre.adventofcode.year2024.day2.Part1.isSafe;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }
    @Override
    public String solve() {
        Part1.Day2FileContents fileContents = getFileContents();
        int totalSafe = 0;
        for(List<Integer> row: fileContents.rows()) {
            if(isModifiableSafe(row)) totalSafe++;
        }

        return String.valueOf(totalSafe);
    }

    static boolean isModifiableSafe(List<Integer> nums) {
        if(isSafe(nums)) return true;
        ArrayList<Integer> modified = new ArrayList<>(nums.subList(1, nums.size()));
        if(isSafe(modified)) {
            return true;
        }
        for(int i = 0; i < nums.size()-1; i++) {
            modified.set(i, nums.get(i));
            if(isSafe(modified)) return true;
        }
        return false;
    }
}
