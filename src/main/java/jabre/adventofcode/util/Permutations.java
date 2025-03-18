package jabre.adventofcode.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Permutations {
    private Permutations() {
    }

    public static <E> void heapsAlgorithm(List<E> values, Consumer<List<E>> permutationConsumer) {
        heapsAlgorithm(values.size(), new ArrayList<>(values), permutationConsumer);
    }

    private static <E> void swap(List<E> values, int k1, int k2) {
        if(k1 == k2) return;
        E temp = values.get(k1);
        values.set(k1, values.get(k2));
        values.set(k2, temp);
    }

    private static <E> void heapsAlgorithm(int k, ArrayList<E> values, Consumer<List<E>> permutationConsumer) {
        if(k == 1) permutationConsumer.accept(values);
        else {
            heapsAlgorithm(k-1, values, permutationConsumer);
            for(int i = 0; i < k-1; i++) {
                if(k%2 == 0) {
                    swap(values, i, k-1);
                } else {
                    swap(values, 0, k-1);
                }
                heapsAlgorithm(k-1, values, permutationConsumer);
            }
        }
    }
}
