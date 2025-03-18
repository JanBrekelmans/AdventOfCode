package jabre.adventofcode.year2015.day03;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.Coordinate2D;
import jabre.adventofcode.util.IntBox;

import java.util.HashMap;
import java.util.Map;

import static jabre.adventofcode.year2015.day03.Part1.*;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day3FileContents fileContents = getFileContents();
        Map<Coordinate2D, IntBox> numberOfPresentsReceived = new HashMap<>();
        int xS = 0, yS = 0, xR = 0, yR = 0;
        numberOfPresentsReceived.computeIfAbsent(new Coordinate2D(xS,yS), k -> new IntBox()).element++;
        numberOfPresentsReceived.computeIfAbsent(new Coordinate2D(xR,yR), k -> new IntBox()).element++;
        for(int i = 0; i < fileContents.path().length(); i += 2) {
            char cS = fileContents.path().charAt(i);
            char cR = fileContents.path().charAt(i+1);

            xS += dx(cS);
            yS += dy(cS);
            numberOfPresentsReceived.computeIfAbsent(new Coordinate2D(xS,yS), k -> new IntBox()).element++;

            xR += dx(cR);
            yR += dy(cR);
            numberOfPresentsReceived.computeIfAbsent(new Coordinate2D(xR,yR), k -> new IntBox()).element++;
        }
        return String.valueOf(numberOfPresentsReceived.size());
    }


}
