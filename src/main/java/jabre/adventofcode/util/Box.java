package jabre.adventofcode.util;

public class Box<T> {
    public T element;

    public Box(T element) {
        this.element = element;
    }

    public Box(){
        this(null);
    }

    public static <T> void swap(Box<T> b1, Box<T> b2) {
        T temp = b1.element;
        b1.element = b2.element;
        b2.element = temp;
    }
}
