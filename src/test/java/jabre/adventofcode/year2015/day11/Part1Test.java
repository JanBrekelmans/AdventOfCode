package jabre.adventofcode.year2015.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Part1Test {
    @Test
    public void customAdditionTest() {
        String current = "xx";

        String next = Part1.increment(current);
        assertEquals("xy", next);

        next = Part1.increment(next);
        assertEquals("xz", next);

        next = Part1.increment(next);
        assertEquals("ya", next);

        next = Part1.increment(next);
        assertEquals("yb", next);
    }

    @Test
    public void customAdditionWrapAroundTest() {
        String current = "zz";

        String next = Part1.increment(current);
        assertEquals("aaa", next);
    }

    @Test
    public void rulesTest() {
        String s1 = "hijklmmn";
        assertTrue(Part1.increasingStraightRule.satisfies(s1));
        assertFalse(Part1.forbiddenLettersRule.satisfies(s1));
        assertFalse(Part1.repeatingDigitsRule.satisfies(s1));

        String s2 = "abbceffg";
        assertFalse(Part1.increasingStraightRule.satisfies(s2));
        assertTrue(Part1.forbiddenLettersRule.satisfies(s2));
        assertTrue(Part1.repeatingDigitsRule.satisfies(s2));

        String s3 = "abbcegjk";
        assertFalse(Part1.increasingStraightRule.satisfies(s3));
        assertTrue(Part1.forbiddenLettersRule.satisfies(s3));
        assertFalse(Part1.repeatingDigitsRule.satisfies(s3));
    }

    @Test
    public void findNextPasswords() {
        String start1 = "abcdefgh";
        assertEquals("abcdffaa", Part1.findNext(start1));

        String start2 = "ghijklmn";
        assertEquals("ghjaabcc", Part1.findNext(start2));
    }
}
