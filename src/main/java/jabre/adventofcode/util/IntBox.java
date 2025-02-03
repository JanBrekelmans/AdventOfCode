package jabre.adventofcode.util;

public class IntBox {
    public int element;
    public IntBox(int element) {
        this.element = element;
    }
    public IntBox() {
        this(0);
    }

    @Override
    public int hashCode() {
        return element;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntBox ib && element == ib.element;
    }

    @Override
    public String toString() {
        return Integer.toString(element);
    }
}
