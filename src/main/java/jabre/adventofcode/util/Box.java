package jabre.adventofcode.util;

public class Box<T> {
    public T element;

    public Box(T element) {
        this.element = element;
    }

    public Box(){
        this(null);
    }
}
