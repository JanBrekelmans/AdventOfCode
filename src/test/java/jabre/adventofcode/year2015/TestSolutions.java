package jabre.adventofcode.year2015;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSolutions {
    @Test
    public void day1() {
        assertEquals("232", new jabre.adventofcode.year2015.day1.Part1().solve());
        assertEquals("1783", new jabre.adventofcode.year2015.day1.Part2().solve());
    }

    @Test
    public void day2() {
        assertEquals("1598415", new jabre.adventofcode.year2015.day2.Part1().solve());
        assertEquals("3812909", new jabre.adventofcode.year2015.day2.Part2().solve());
    }

    @Test
    public void day3() {
        assertEquals("2592", new jabre.adventofcode.year2015.day3.Part1().solve());
        assertEquals("2360", new jabre.adventofcode.year2015.day3.Part2().solve());
    }

    @Test
    public void day4() {
        assertEquals("117946", new jabre.adventofcode.year2015.day4.Part1().solve());
        assertEquals("3938038", new jabre.adventofcode.year2015.day4.Part2().solve());
    }

    @Test
    public void day5() {
        assertEquals("236", new jabre.adventofcode.year2015.day5.Part1().solve());
        assertEquals("51", new jabre.adventofcode.year2015.day5.Part2().solve());
    }

    @Test
    public void day6() {
        assertEquals("400410", new jabre.adventofcode.year2015.day6.Part1().solve());
        assertEquals("15343601", new jabre.adventofcode.year2015.day6.Part2().solve());
    }

    @Test
    public void day7() {
        assertEquals("46065", new jabre.adventofcode.year2015.day7.Part1().solve());
        assertEquals("14134", new jabre.adventofcode.year2015.day7.Part2().solve());
    }

    @Test public void day8() {

    }

    @Test public void day9() {
        assertEquals("141.0", new jabre.adventofcode.year2015.day9.Part1().solve());
        assertEquals("736.0", new jabre.adventofcode.year2015.day9.Part2().solve());
    }
}
