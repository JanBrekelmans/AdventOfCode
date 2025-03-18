package jabre.adventofcode.year2015.day12;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jabre.adventofcode.Solution;

import java.io.IOException;
import java.util.Iterator;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day12FileContents fileContents = Part1.getFileContents();
        JsonNode node;
        try {
            ObjectMapper obj = new ObjectMapper();
            node = obj.readTree(fileContents.json());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(getSum(node));
    }

    int getSum(JsonNode node) {
        if(node.isObject()) {
            int sum = 0;
            for(Iterator<JsonNode> it = node.iterator(); it.hasNext();) {
                var n = it.next();
                if(n.isTextual()) {
                    if(n.textValue().equals("red")) return 0;
                }
                sum += getSum(n);
            }
            return sum;
        } else if (node.isArray()) {
            int sum = 0;
            for(Iterator<JsonNode> it = node.iterator(); it.hasNext();) {
                var n = it.next();
                sum += getSum(n);
            }
            return sum;
        } else if(node.isNumber()) {
            return node.intValue();
        }
        return 0;
    }
}
