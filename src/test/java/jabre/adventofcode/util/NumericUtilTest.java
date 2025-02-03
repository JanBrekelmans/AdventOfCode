package jabre.adventofcode.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumericUtilTest {
    List<Integer> positiveMonotonicSequence = List.of(1,1,2,3);
    List<Integer> negativeMonotonicSequence = List.of(-1,-1,-2,-3);
    List<Integer> positiveStrictlyMonotonicSequence = List.of(1,2,3);
    List<Integer> negativeStrictlyMonotonicSequence = List.of(-1,-2,-3);
    List<Integer> nonMonotonicSequence = List.of(1,4,2,3);

    @Test
    public void testMonotonicity() {
        assertTrue(NumericUtil.isMonotonic(positiveMonotonicSequence));
        assertTrue(NumericUtil.isMonotonic(negativeMonotonicSequence));
        assertTrue(NumericUtil.isMonotonic(positiveStrictlyMonotonicSequence));
        assertTrue(NumericUtil.isMonotonic(negativeStrictlyMonotonicSequence));
        assertFalse(NumericUtil.isMonotonic(nonMonotonicSequence));

        assertFalse(NumericUtil.isStrictlyMonotonic(positiveMonotonicSequence));
        assertFalse(NumericUtil.isStrictlyMonotonic(negativeMonotonicSequence));
        assertTrue(NumericUtil.isStrictlyMonotonic(positiveStrictlyMonotonicSequence));
        assertTrue(NumericUtil.isStrictlyMonotonic(negativeStrictlyMonotonicSequence));
        assertFalse(NumericUtil.isStrictlyMonotonic(nonMonotonicSequence));
    }
}
