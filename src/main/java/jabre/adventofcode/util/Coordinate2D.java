package jabre.adventofcode.util;

public record Coordinate2D(int x, int y) {
    public Coordinate2D(String x, String y) {
        this(Integer.parseInt(x), Integer.parseInt(y));
    }
}
