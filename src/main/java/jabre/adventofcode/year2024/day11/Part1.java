package jabre.adventofcode.year2024.day11;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.IntBox;
import jabre.adventofcode.util.NumericUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

public class Part1 implements Solution {
    private final Map<BlinkSnap, Long> numberOfStones = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day11FileContents contents = getFileContents();
        List<Integer> current = contents.numbers;
        return String.valueOf(determineTotalEndingStones(current, 25));
    }

    public long determineTotalEndingStones(List<Integer> startingStoneNumbers, int totalBlinks) {
        long total = 0;
        for (Integer s : startingStoneNumbers) {
            total += determineNumberOfEndingStones(new BlinkSnap(totalBlinks, s));
        }
        return total;
    }


    static Day11FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day11.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath())) {
            return new Day11FileContents(Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    long determineNumberOfEndingStones(BlinkSnap current) {
        if(numberOfStones.containsKey(current)) {
            return numberOfStones.get(current);
        }

        if(current.stoneNumber == 0) {
            if(current.blinkNumber == 1) {
                numberOfStones.put(current, 1L);
            } else {
                numberOfStones.put(current, determineNumberOfEndingStones(new BlinkSnap(current.blinkNumber - 1, 1L)));
            }
        } else if (Long.toString(current.stoneNumber).length()%2 == 0) {
            if(current.blinkNumber == 1) {
                numberOfStones.put(current, 2L);
            } else {
                long total = 0;
                for(var t: transform(current.stoneNumber)) {
                    total += determineNumberOfEndingStones(new BlinkSnap(current.blinkNumber - 1, t));
                }
                numberOfStones.put(current, total);
            }
        } else {
            if(current.blinkNumber == 1) {
                numberOfStones.put(current, 1L);
            } else {
                numberOfStones.put(current, determineNumberOfEndingStones(new BlinkSnap(current.blinkNumber - 1, 2024L * current.stoneNumber)));
            }
        }

        return numberOfStones.get(current);
    }

    static long[] transform(long n) {
        if (n == 0) {
            return new long[]{1L};
        }
        int[] digits = NumericUtil.digits(n);
        if (digits.length % 2 == 0) {
            return new long[]{
                    NumericUtil.fromDigits(Arrays.copyOfRange(digits, 0, digits.length / 2)),
                    NumericUtil.fromDigits(Arrays.copyOfRange(digits, digits.length / 2, digits.length))
            };
        }
        return new long[]{2024L * n};
    }

    record Day11FileContents(List<Integer> numbers) {
    }

    record BlinkSnap(int blinkNumber, long stoneNumber) {
    }
}
