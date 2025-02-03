package jabre.adventofcode.year2024.day2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static jabre.adventofcode.year2024.day2.Part2.isModifiableSafe;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Part2Test {
    List<Integer> safe1 = List.of(7,6,4,2,1);
    List<Integer> safe2 = List.of(1,3,6,7,9);
    List<Integer> unconditionalUnsafe1 = List.of(1,2,7,8,9);
    List<Integer> unconditionalUnsafe2 = List.of(9,7,6,2,1);
    List<Integer> modifiableSafe1 = List.of(1,3,2,4,5);
    List<Integer> modifableSafe2 = List.of(8,6,4,4,1);
    @Test
    public void testModifiableSafety() {
        assertTrue(isModifiableSafe(safe1));
        assertTrue(isModifiableSafe(safe2));
        assertFalse(isModifiableSafe(unconditionalUnsafe1));
        assertFalse(isModifiableSafe(unconditionalUnsafe2));
        assertTrue(isModifiableSafe(modifiableSafe1));
        assertTrue(isModifiableSafe(modifableSafe2));
    }
}
