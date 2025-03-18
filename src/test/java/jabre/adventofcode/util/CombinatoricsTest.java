package jabre.adventofcode.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombinatoricsTest {
    @Test
    public void numberOfDifferentContainerCombinations() {
        assertEquals(73682, Combinatorics.numberOfContainerCombinations(List.of(1, 2, 5, 10, 20, 50, 100, 200), 200));

    }
}
