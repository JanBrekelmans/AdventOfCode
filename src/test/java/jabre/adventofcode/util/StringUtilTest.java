package jabre.adventofcode.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilTest {
    @Test
    public void splitOnRepeatingCharacters() {
        String s1 = "s";
        assertEquals(List.of("s"), StringUtil.splitStringOnRepeatingCharacters(s1));

        String s2 = "sssss";
        assertEquals(List.of("sssss"), StringUtil.splitStringOnRepeatingCharacters(s2));

        String s3 = "ssssstttt";
        assertEquals(List.of("sssss", "tttt"), StringUtil.splitStringOnRepeatingCharacters(s3));

        String s4 = "tsstttssss";
        assertEquals(List.of("t", "ss", "ttt", "ssss"), StringUtil.splitStringOnRepeatingCharacters(s4));
    }
}
