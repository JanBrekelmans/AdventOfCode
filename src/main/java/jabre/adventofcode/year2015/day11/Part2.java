package jabre.adventofcode.year2015.day11;

import jabre.adventofcode.Solution;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        String start = "hxbxwxba";
        String next = Part1.findNext(start);
        String nextNext = Part1.findNext(next);
        return nextNext;
    }
}
