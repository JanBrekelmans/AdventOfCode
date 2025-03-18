package jabre.adventofcode.util;

import java.util.List;

public class Combinatorics {
    private Combinatorics(){}

    public static int numberOfContainerCombinations(List<Integer> containerSizes, int totalSize) {
        if(containerSizes.stream().anyMatch(s -> s <= 0)) throw new RuntimeException("Container sizes must be positive");
        int[] numberOfWays = new int[totalSize + 1];
        numberOfWays[0] = 1;

        for(Integer size: containerSizes) {
            for(int i = size; i <= totalSize; i++) {
                numberOfWays[i] += numberOfWays[i - size];
            }
        }
        return numberOfWays[totalSize];
    }


}
