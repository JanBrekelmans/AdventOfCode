package jabre.adventofcode.year2018.day02;

import jabre.adventofcode.Solution;

import java.util.HashSet;
import java.util.Set;

import static jabre.adventofcode.year2018.day02.Part1.getFileContents;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day2FileContents fileContents = getFileContents();
        Set<String> seen = new HashSet<>();

        for(String id: fileContents.ids()) {
            for(int i = 0; i < id.length(); i++) {
                String ignoreOneLetter = id.substring(0, i) + "*" + id.substring(i+1);
                if(seen.contains(ignoreOneLetter)) return id.substring(0,i) + id.substring(i+1);
                seen.add(ignoreOneLetter);
            }
        }

        return null;
    }
}
