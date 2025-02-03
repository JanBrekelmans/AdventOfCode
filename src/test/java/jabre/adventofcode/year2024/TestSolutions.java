package jabre.adventofcode.year2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSolutions {
    @Test
    public void dayOne() {
        assertEquals(new jabre.adventofcode.year2024.day1.Part1().solve(), "2344935");
        assertEquals(new jabre.adventofcode.year2024.day1.Part2().solve(), "27647262");
    }

    @Test
    public void dayTwo() {
        assertEquals(new jabre.adventofcode.year2024.day2.Part1().solve(), "472");
        assertEquals(new jabre.adventofcode.year2024.day2.Part2().solve(), "520");
    }

    @Test
    public void dayThree() {
        assertEquals(new jabre.adventofcode.year2024.day3.Part1().solve(), "181345830");
        assertEquals(new jabre.adventofcode.year2024.day3.Part2().solve(), "98729041");
    }

    @Test
    public void dayEleven() {
        assertEquals(new jabre.adventofcode.year2024.day11.Part1().solve(), "194782");
        assertEquals(new jabre.adventofcode.year2024.day11.Part2().solve(), "233007586663131");
    }
}
