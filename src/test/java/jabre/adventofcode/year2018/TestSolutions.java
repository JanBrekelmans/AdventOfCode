package jabre.adventofcode.year2018;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSolutions {
    @Test
    public void testDay1() {
        assertEquals("445", new jabre.adventofcode.year2018.day01.Part1().solve());
        assertEquals("219", new jabre.adventofcode.year2018.day01.Part2().solve());
    }

    @Test
    public void testDay2() {
        assertEquals("7688", new jabre.adventofcode.year2018.day02.Part1().solve());
        assertEquals("lsrivmotzbdxpkxnaqmuwcchj", new jabre.adventofcode.year2018.day02.Part2().solve());
    }
}
