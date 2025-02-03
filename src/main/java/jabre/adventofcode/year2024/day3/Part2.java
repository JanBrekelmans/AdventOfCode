package jabre.adventofcode.year2024.day3;

import jabre.adventofcode.Solution;

import java.util.ArrayList;
import java.util.List;

import static jabre.adventofcode.year2024.day3.Part1.getFileContents;
import static jabre.adventofcode.year2024.day3.Part1.getMultiplicationResults;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day3FileContents fileContents = getFileContents();
        String totalText = String.join("\n", fileContents.lines());
        List<String> lines = splitText(totalText);
        return String.valueOf(getMultiplicationResults(lines));
    }

    static List<String> splitText(String text) {
        boolean safe = true;
        StringBuilder sb = new StringBuilder();
        String doString = "do()";
        String dontString = "don't()";
        List<String> lines = new ArrayList<>();

        for(int i = 0; i < text.length();) {
                if(text.startsWith(doString, i)) {
                    if(!safe) {
                        safe = true;
                        sb = new StringBuilder();
                    }
                    i += doString.length();
                } else if (text.startsWith(dontString, i)) {
                    if(safe) {
                        safe = false;
                        lines.add(sb.toString());
                    }
                    i += dontString.length();
                } else {
                    sb.append(text.charAt(i));
                    i++;
                }
        }

        if(safe) {
            lines.add(sb.toString());
        }
        return lines;
    }
}
