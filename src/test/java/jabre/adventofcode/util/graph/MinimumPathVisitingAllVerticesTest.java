package jabre.adventofcode.util.graph;

import jabre.adventofcode.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumPathVisitingAllVerticesTest {

    @Test
    public void test() {
        Map<Integer, String> idToNameMap = new HashMap<>();
        Graph.Vertex dublin = new Graph.Vertex(0);
        idToNameMap.put(dublin.id(), "DUBLIN");

        Graph.Vertex london = new Graph.Vertex(1);
        idToNameMap.put(london.id(), "LONDON");

        Graph.Vertex belfast = new Graph.Vertex(2);
        idToNameMap.put(belfast.id(), "BELFAST");

        Function<Graph.Vertex, Stream<Graph.Edge>> connectivitySupplier = v -> switch (idToNameMap.get(v.id())) {
            case "DUBLIN" -> Stream.of(new Graph.Edge(dublin, london, 464.0),
                    new Graph.Edge(dublin, belfast, 141.0));
            case "LONDON" -> Stream.of(new Graph.Edge(london, dublin, 464.0),
                            new Graph.Edge(london, belfast, 518.0));
            case "BELFAST" -> Stream.of(new Graph.Edge(belfast, dublin, 141.0),
                            new Graph.Edge(belfast, london, 518.0));
            default -> throw new IllegalStateException("Unexpected value: " + idToNameMap.get(v.id()));
        };

        var path = Graph.findMinimumPathVisitingAllVertices(List.of(dublin, london, belfast), connectivitySupplier);
        assertEquals(605.0, path.totalCost());
    }
}
