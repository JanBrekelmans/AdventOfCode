package jabre.adventofcode.year2015.day04;

import jabre.adventofcode.Solution;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static jabre.adventofcode.util.NumericUtil.byteArrayToHex;

public class Part1 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }
    @Override
    public String solve() {
        String key = "ckczppom";
        return String.valueOf(determineKey(key, 5));
    }

    static int determineKey(String key, int numberOfZeroes) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            for(int i = 0;; i++) {
                String msg = key + i;
                md.update(msg.getBytes());
                byte[] digest = md.digest();
                String hash = byteArrayToHex(digest);
                if(hash.startsWith("0".repeat(numberOfZeroes))) return i;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
