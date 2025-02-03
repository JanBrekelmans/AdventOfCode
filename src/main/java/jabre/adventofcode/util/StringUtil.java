package jabre.adventofcode.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    private StringUtil(){}

    public static List<String> splitStringOnRepeatingCharacters(String s) {
        if(s.isBlank()) return List.of();
        int tail = 0;
        int head = 0;
        List<String> sections = new ArrayList<>();
        while(true) {
            head++;
            if(head == s.length()) {
                sections.add(s.substring(tail, head));
                break;
            }
            if(s.charAt(head) != s.charAt(head - 1)) {
                sections.add(s.substring(tail, head));
                tail = head;
            }
        }
        return sections;
    }
}
