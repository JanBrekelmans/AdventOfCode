package jabre.adventofcode.year2024.day11;

import org.junit.jupiter.api.Test;

import java.util.List;

import static jabre.adventofcode.year2024.day11.Part1.transform;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Part1Test {
    @Test
    public void testRules() {
        assertArrayEquals(new long[]{1}, transform(0));
        assertArrayEquals(new long[]{2024}, transform(1));
        assertArrayEquals(new long[]{1, 0}, transform(10));
        assertArrayEquals(new long[]{9,9}, transform(99));
        assertArrayEquals(new long[]{2021976}, transform(999));
        assertArrayEquals(new long[]{10, 0}, transform(1000));
    }

    @Test
    public void testExample() {
        assertEquals(22, new Part1().determineTotalEndingStones(List.of(125, 17), 6));
        assertEquals(55312, new Part1().determineTotalEndingStones(List.of(125, 17), 25));
    }
}
