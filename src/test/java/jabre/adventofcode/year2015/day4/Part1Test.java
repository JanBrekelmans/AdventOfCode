package jabre.adventofcode.year2015.day4;

import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Part1Test {
    @Test
    public void md5Test1(){
        String key = "abcdef";
        assertEquals(609043, Part1.determineKey(key, 5));
    }

    @Test
    public void md5Test2(){
        String key = "pqrstuv";
        assertEquals(1048970, Part1.determineKey(key, 5));
    }
}
