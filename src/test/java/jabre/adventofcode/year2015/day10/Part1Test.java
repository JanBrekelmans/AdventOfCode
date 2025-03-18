package jabre.adventofcode.year2015.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Part1Test {
    @Test
    public void test() {
        String start = "1";

        String iteration1 = Part1.determineNextIteration(start);
        assertEquals(iteration1, "11");

        String iteration2 = Part1.determineNextIteration(iteration1);
        assertEquals(iteration2, "21");

        String iteration3 = Part1.determineNextIteration(iteration2);
        assertEquals(iteration3, "1211");

        String iteration4 = Part1.determineNextIteration(iteration3);
        assertEquals(iteration4, "111221");

        String iteration5 = Part1.determineNextIteration(iteration4);
        assertEquals(iteration5, "312211");
    }
}
