package jabre.adventofcode.util;

import java.util.ArrayList;
import java.util.List;

public final class NumericUtil {
    private NumericUtil(){}

    public static boolean isMonotonic(List<Integer> nums) {
        if(nums.size() <= 1) return true;
        List<Integer> offsets = getOffsets(nums);
        int firstSignum = 0;
        int i = 0;
        for(; i < offsets.size(); i++) {
            if(signum(offsets.get(i)) != 0) {
                firstSignum = signum(offsets.get(i));
                break;
            }
        }
        for(; i < offsets.size(); i++) {
            int signum = signum(offsets.get(i));
            if(signum != 0 && signum != firstSignum) {
                return false;
            }
        }
        return true;
    }

    public static boolean isStrictlyMonotonic(List<Integer> nums) {
        if(nums.size() <= 1) return true;
        List<Integer> offsets = getOffsets(nums);
        int firstSignum = signum(offsets.get(0));
        if(firstSignum == 0) return false;
        for(int i = 1; i < offsets.size(); i++) {
            int signum = signum(offsets.get(i));
            if(signum == 0 || signum != firstSignum) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> getOffsets(List<Integer> nums) {
        List<Integer> offsets = new ArrayList<>();
        for(int i = 0; i < nums.size() - 1; i++) {
            offsets.add(nums.get(i) - nums.get(i+1));
        }
        return offsets;
    }

    public static int signum(int a) {
        if(a < 0) return -1;
        if (a > 0) return 1;
        return 0;
    }

    public static int[] digits(int n) {
        return String.valueOf(n).chars().map(i -> i - '0').toArray();
    }

    public static int[] digits(long n) {
        return String.valueOf(n).chars().map(i -> i - '0').toArray();
    }

    public static int fromDigits(int[] digits) {
        int n = 0;
        for(int i = 0; i < digits.length; i++) {
            n *= 10;
            n += digits[i];
        }
        return n;
    }

    public static long fromDigits(long[] digits) {
        int n = 0;
        for(int i = 0; i < digits.length; i++) {
            n *= 10;
            n += digits[i];
        }
        return n;
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    public static boolean isNumber(String s) {
        return s.matches("(\\d*)");
    }
}
